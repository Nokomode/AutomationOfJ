namespace LoginPage;

public partial class ManagerDashboardPage : ContentPage
{
	private readonly AuthService _authService;
	public ManagerDashboardPage(AuthService authService)
	{
		InitializeComponent();
		_authService = authService;
	}
}