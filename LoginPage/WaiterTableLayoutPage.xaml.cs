namespace LoginPage;

public partial class WaiterTableLayoutPage : ContentPage
{
	public WaiterTableLayoutPage()
	{
		InitializeComponent();
	}

    private void ShowTablePopUpMenu(object sender, EventArgs e)
	{
		
		PopUpButton.IsVisible = true;
		TablePopUp.IsVisible = true;
	}

	private void Button_Clicked(object sender, EventArgs e)
	{
		TablePopUp.IsVisible = false;
		PopUpButton.IsVisible = false;
	}
}