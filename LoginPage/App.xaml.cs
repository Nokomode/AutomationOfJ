using Microsoft.Extensions.DependencyInjection;

namespace LoginPage
{
    public partial class App : Application
    {
        private readonly AuthService _authService;
        public App(AuthService authService)
        {
            _authService = authService;
            InitializeComponent();
        }

        protected override Window CreateWindow(IActivationState? activationState)
        {
            var window = new Window(new AppShell());

            window.Destroying += async (sender, e) =>
            {
                if (UserSession.EmployeeID != null)
                {
                    await _authService.Logout(UserSession.EmployeeID);
                    UserSession.Clear();
                }
            };

            return window;
        }
    }
}