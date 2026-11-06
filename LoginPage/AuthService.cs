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

        // Takes username and password, sends them to the backend, and returns a LoginResponse if successful, or null if failed.
        public async Task<LoginResponse> LoginAsync(string username, string password)
        {


            var loginRequest = new LoginRequest
            {
                EmployeeID = username,
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

            HttpResponseMessage response;
            try
            {
                response =
                    await client.PostAsync(
                        "http://localhost:8080/api/auth/login",
                        content
                    );
            }
            catch (HttpRequestException ex)
            {
                
                Debug.WriteLine($"Request error: {ex.Message}");
                return null;
            }

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

        // Takes a User object, sends it to the backend to register, and returns true if successful, false if failed.
        public async Task<bool> RegisterAsync(RegisterRequest newUser)
        {
     
            string json = JsonSerializer.Serialize(newUser, new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase
            });

            var content = new StringContent(
                json,
                Encoding.UTF8,
                "application/json"
            );


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

        // Takes a username, sends it to the backend to log out, and returns true if successful, false if failed.
        public async Task<bool> Logout(string username)
        {
        


            HttpResponseMessage response;
            try
            {
                response = await client.GetAsync(
                    $"http://localhost:8080/api/auth/logout/{username}"
                );
            }
            catch (HttpRequestException ex)
            {
                Debug.WriteLine($"Request error: {ex.Message}");
                return false;
            }


            if (response.IsSuccessStatusCode)
            {
                Debug.WriteLine($"Status Code: {response.StatusCode}");
                string responseJson = await response.Content.ReadAsStringAsync();

                Debug.WriteLine($"Response: {responseJson}");

                
                return true;
            }
            else
            {
                Debug.WriteLine($"Response: {response}");
                return false;
            }
        }

    }
}
