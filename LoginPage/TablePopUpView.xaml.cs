namespace LoginPage;

public partial class TablePopUpView : ContentView
{ 
	public TablePopUpView()
	{
		InitializeComponent();
	}
    private void CloseButton_Clicked(object sender, EventArgs e)
	{
		BorderPopUp.IsVisible = false;
	}

	private void AddOrderButton_Clicked(object sender, EventArgs e)
	{
		Navigation.PushAsync(new AddOrderMenuPage());
	}

	private void Cancel_ViewButton_Clicked(object sender, EventArgs e)
	{
		BorderPopUp.IsVisible = false;
	}

	private void CheckoutButton_Clicked(object sender, EventArgs e)
	{
		Navigation.PushAsync(new CheckoutPaymentMethodPage());
	}
}