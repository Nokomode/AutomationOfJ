using System;
using Microsoft.Maui.Controls;
using System;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace LoginPage
{
    public partial class RegisterPage : ContentPage
    {
        private readonly AuthService _authService;
        public RegisterPage(AuthService authService)
        {
            InitializeComponent();
            _authService = authService;
        }

        private async void OnRegisterClicked(object sender, EventArgs e)
        {
            Debug.WriteLine("Register button clicked");
            var username = UsernameEntry?.Text;
            var password = PasswordEntry?.Text;
            var confirm = ConfirmPasswordEntry?.Text;
            var role = RolePicker?.SelectedItem?.ToString();
            if (string.IsNullOrWhiteSpace(username) ||
                string.IsNullOrWhiteSpace(password) ||
                string.IsNullOrWhiteSpace(role) ||
                password != confirm)
            {
                await DisplayAlertAsync("Error", "Registration failed. Username may already exist.", "OK");
                return;
            }


            User newUser = new User
            {
                Username = username,
                Password = password,
                Role = role
            };

            HttpClient client = new HttpClient();
            AuthService authService = new AuthService(client);
            bool success = await authService.RegisterAsync(newUser);

            if (success)
            {
                await Shell.Current.GoToAsync($"//{nameof(LoginPage)}");
            }
            else

            {
                Debug.WriteLine("Registration failed.");
            }

            
            
        }
    }
}