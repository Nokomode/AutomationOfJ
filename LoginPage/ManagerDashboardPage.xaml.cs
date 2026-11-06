namespace LoginPage;

public partial class ManagerDashboardPage : ContentPage
{
	private readonly AuthService _authService;
	private readonly UserService _userService;

    public ManagerDashboardPage(AuthService authService, UserService userService, TopBarView topBar)
	{
		InitializeComponent();
		_authService = authService;
		_userService = userService;	
        TopBarContainer.Content = topBar;
        topBar.SetDashboardUnderline();
		SetWelcomeLabel();
    }

	public async void SetWelcomeLabel()
	{
		var user = await _userService.GetUserByEmployeeID(UserSession.EmployeeID);
		if (user != null)
		{
			WelcomeLabel.Text = $"Welcome, Manager {user.FirstName}!";
		}
		else
		{
			WelcomeLabel.Text = "Welcome!";
		}
    }
}