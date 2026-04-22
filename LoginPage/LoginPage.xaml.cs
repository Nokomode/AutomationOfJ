using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace LoginPage
{
    public partial class LoginPage : ContentPage
    {

        private readonly AuthService _authService;
        public LoginPage(AuthService authService)
        {
            InitializeComponent();
            _authService = authService;
        }

        private async void OnLoginClicked(object? sender, EventArgs e)
        {
            LoginErrorLabel.IsVisible = false;
            
            LoginResponse result = await _authService.LoginAsync(UsernameField.Text, PasswordField.Text);

            if (result != null)
            {
                UserSession.Role = result.Role;
                UserSession.EmployeeID = result.EmployeeID;
                // navigate to the right page

                switch (UserSession.Role)
                {
                    case "Manager":
                        await Shell.Current.GoToAsync($"//{nameof(ManagerDashboardPage)}");
                        break;
                    case "Wait Staff":
                        //await Shell.Current.GoToAsync($"//{nameof(WaiterDashboardPage)}");
                        break;
                    case "Chef":
                        //await Shell.Current.GoToAsync($"//{nameof(ChefDashboardPage)}");
                        break;
                    case "Busboy":
                        //await Shell.Current.GoToAsync($"//{nameof(BusboyDashboardPage)}");
                        break;
                }
            }
            else
            {
                LoginErrorLabel.IsVisible = true;
                //await DisplayAlertAsync("Error", "Incorrect Login Information", "OK");
                return;
                
            }
        }

        private async void OnRegisterClicked(object? sender, EventArgs e)
        {
            await Navigation.PushAsync(new RegisterPage(_authService));
        }
        
    }
}
