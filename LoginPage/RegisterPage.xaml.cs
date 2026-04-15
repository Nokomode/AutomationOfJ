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

            // Reset all error states
            FirstNameAsterisk.IsVisible = false;
            LastNameAsterisk.IsVisible = false;
            EmailAsterisk.IsVisible = false;
            PhoneAsterisk.IsVisible = false;
            UsernameAsterisk.IsVisible = false;
            PasswordAsterisk.IsVisible = false;
            ConfirmPasswordAsterisk.IsVisible = false;
            RoleAsterisk.IsVisible = false;
            AllFieldsErrorLabel.IsVisible = false;
            PasswordErrorLabel.IsVisible = false;
            PasswordMismatchLabel.IsVisible = false;





            bool hasEmpty = false;

            // Check empty fields and show asterisks
            if (string.IsNullOrWhiteSpace(FirstNameEntry.Text)) { FirstNameAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(LastNameEntry.Text)) { LastNameAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(EmailEntry.Text)) { EmailAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(PhoneEntry.Text)) { PhoneAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(UsernameEntry.Text)) { UsernameAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(PasswordEntry.Text)) { PasswordAsterisk.IsVisible = true; hasEmpty = true; }
            if (string.IsNullOrWhiteSpace(ConfirmPasswordEntry.Text)) { ConfirmPasswordAsterisk.IsVisible = true; hasEmpty = true; }
            if (RolePicker.SelectedIndex == -1) { RoleAsterisk.IsVisible = true; hasEmpty = true; }

            if (hasEmpty)
            {
                await DisplayAlertAsync("Error", "Please Fill Out All Fields", "OK");
                AllFieldsErrorLabel.IsVisible = true;
                return;
            }

            // Password complexity: 8+ chars, 1 number, 1 special character
            var password = PasswordEntry.Text;
            bool validPassword = password.Length >= 8
                && password.Any(char.IsDigit)
                && password.Any(c => !char.IsLetterOrDigit(c));

            if (!validPassword)
            {
                await DisplayAlertAsync("Error", "Password must be at least 8 characters long and contain at least one number and one special character.", "OK");
                PasswordErrorLabel.IsVisible = true;
                return;
            }

            // Password match
            if (password != ConfirmPasswordEntry.Text)
            {
                await DisplayAlertAsync("Error", "Passwords do not match.", "OK");
                PasswordMismatchLabel.IsVisible = true;
                return;
            }




            User newUser = new User
            {
                EmployeeID = UsernameEntry.Text,
                Password = PasswordEntry.Text,
                Role = RolePicker.SelectedItem.ToString(),
                Email = EmailEntry.Text,
                Phone = PhoneEntry.Text,
                FirstName = FirstNameEntry.Text,
                LastName = LastNameEntry.Text,
                Salary = 11 // Default salary, can be updated later by admin
            };

            bool success = await _authService.RegisterAsync(newUser);

            if (success)
            {
                await Shell.Current.GoToAsync($"//{nameof(LoginPage)}");
            }
            else

            {
                await DisplayAlertAsync("Error", "HTTP Connection Timed Out", "OK");
            }

            
            
        }
    }
}