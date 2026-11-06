namespace LoginPage;

public partial class TopBarView : ContentView
{
    private readonly AuthService _authService;
	public TopBarView(AuthService authService)
	{
		InitializeComponent();
        _authService = authService;
        ManagerLabel.Text = "Manager, " + UserSession.FirstName;
    }
    // These methods set the visibility of the underline indicators for each button in the top bar.
    public void SetDashboardUnderline()
    {
        DashboardUnderline.IsVisible = true;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetTableLayoutUnderline() 
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = true;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetInventoryUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = true;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetSalesUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = true;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetEmployeeProfilesUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = true;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetRefundsUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = true;
        OrderQueueUnderline.IsVisible = false;
    }

    public void SetOrderQueueUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
        OrderQueueUnderline.IsVisible = true;
    }


    // Depending on which top bar button is clicked, the corresponding method will be called to navigate to the appropriate page and set the underline visibility.
    private async void OnDashboardClicked(Object sender, EventArgs e)
	{
        await Shell.Current.GoToAsync($"//{nameof(ManagerDashboardPage)}");
	}

    private async void OnTableLayoutClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(TableLayoutPage)}");
    }

	private async void OnInventoryClicked(Object sender, EventArgs e)
	{

        await Shell.Current.GoToAsync($"//{nameof(InventoryPage)}");
    }

    private async void OnSalesClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(SalesPage)}");
    }

    private async void OnEmployeeProfilesClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(AllEmployeesPage)}");
    }

    private async void OnRefundsClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(RefundsPage)}");
    }

    private async void OnOrderQueueClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(OrderQueuePage)}");
    }

    private async void OnLogoutClicked(Object sender, EventArgs e)
    {
        
        await _authService.Logout(UserSession.EmployeeID);
        UserSession.Clear();
        await Shell.Current.GoToAsync($"//LoginPage");
    }
}