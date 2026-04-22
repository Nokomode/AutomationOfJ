namespace LoginPage;

public partial class TopBarView : ContentView
{
	public TopBarView()
	{
		InitializeComponent();
	}

	private async void OnDashboardClicked(Object sender, EventArgs e)
	{

	}

	private async void OnInventoryClicked(Object sender, EventArgs e)
	{

	}

    private async void OnSalesClicked(Object sender, EventArgs e)
    {
        await Navigation.PushAsync(new TESTINGPAGE());
    }

    private async void OnEmployeeProfilesClicked(Object sender, EventArgs e)
    {
        await Navigation.PushAsync(new WaiterTableLayoutPage());
    }

    private async void OnRefundsClicked(Object sender, EventArgs e)
    {

    }

    private async void OnLogoutClicked(Object sender, EventArgs e)
    {

    }
}