using System;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace LoginPage
{
    public class AuthService
    {
        private readonly HttpClient client;

        public AuthService(HttpClient httpClient)
        {
            client = httpClient;
        }

        public async Task<LoginResponse> LoginAsync(string username, string password)
        {
            /* ---------- API CALL ---------- */



            var loginRequest = new LoginRequest
            {
                Username = username,
                Password = password
            };

            string json = JsonSerializer.Serialize(loginRequest, new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase
            });

            var content = new StringContent(
                json,
                Encoding.UTF8,
                "application/json"
            );

            HttpResponseMessage response =
                await client.PostAsync(
                    "http://localhost:8080/api/auth/login",
                    content
                );

            if (response.IsSuccessStatusCode)
            {
                string responseJson = await response.Content.ReadAsStringAsync();
                LoginResponse loginResponse = JsonSerializer.Deserialize<LoginResponse>(responseJson, new JsonSerializerOptions
                {
                    PropertyNameCaseInsensitive = true
                });
                return loginResponse;
            }
            else
            {
                return null;
            }
        }

        public async Task<bool> RegisterAsync(User newUser)
        {
            /* ---------- API CALL ---------- */
            string json = JsonSerializer.Serialize(newUser, new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase
            });

            var content = new StringContent(
                json,
                Encoding.UTF8,
                "application/json"
            );


            // System.Net.Http.HttpRequestException: 'No connection could be made because the target machine actively refused it. (localhost:8080)'
            HttpResponseMessage response;
            try
            {
                response =
                    await client.PostAsync(
                        "http://localhost:8080/api/auth/register",
                        content
                    );
            }
            catch (HttpRequestException ex)
            {
                Debug.WriteLine($"Request error: {ex.Message}");
                return false;
            }
            

            Debug.WriteLine(await response.Content.ReadAsStringAsync());
            if (response.IsSuccessStatusCode)
            {
                Debug.WriteLine("Registration successful!");
                return true;
            }
            else
            {
                Debug.WriteLine($"Status Code: {response.StatusCode}");
                Debug.WriteLine(await response.Content.ReadAsStringAsync());
                Debug.WriteLine("Registration failed");
                return false;
            }


        }
    }
}
