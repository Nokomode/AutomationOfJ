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
            HttpClient client = new HttpClient();
            AuthService service = new AuthService(client);
            LoginResponse result = await service.LoginAsync(UsernameField.Text, PasswordField.Text);

            if (result != null)
            {
                UserSession.Role = result.Role;
                UserSession.Username = result.Username;
                // navigate to the right page
            }
            else
            {
                // show error
            }
        }

        private async void OnRegisterClicked(object? sender, EventArgs e)
        {
            await Navigation.PushAsync(new RegisterPage());
        }
    }
}
