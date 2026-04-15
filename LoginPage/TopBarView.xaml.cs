namespace LoginPage;

public partial class TopBarView : ContentView
{
	public TopBarView()
	{
		InitializeComponent();
	}

    public void SetDashboardUnderline()
    {
        DashboardUnderline.IsVisible = true;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
    }

    public void SetTableLayoutUnderline() 
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = true;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
    }

    public void SetInventoryUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = true;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
    }

    public void SetSalesUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = true;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = false;
    }

    public void SetEmployeeProfilesUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = true;
        RefundsUnderline.IsVisible = false;
    }

    public void SetRefundsUnderline()
    {
        DashboardUnderline.IsVisible = false;
        TableLayoutUnderline.IsVisible = false;
        InventoryUnderline.IsVisible = false;
        SalesUnderline.IsVisible = false;
        EmployeeProfilesUnderline.IsVisible = false;
        RefundsUnderline.IsVisible = true;
    }

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
        await Shell.Current.GoToAsync($"//{nameof(EmployeeProfilesPage)}");
    }

    private async void OnRefundsClicked(Object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync($"//{nameof(RefundsPage)}");
    }

    private async void OnLogoutClicked(Object sender, EventArgs e)
    {

    }
}