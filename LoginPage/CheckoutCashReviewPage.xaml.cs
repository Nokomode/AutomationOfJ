namespace LoginPage;

public partial class CheckoutCashReviewPage : ContentPage
{
	public CheckoutCashReviewPage()
	{
		InitializeComponent();
	}

	private async void ConfirmButton_Clicked(object sender, EventArgs e)
	{
		await DisplayAlertAsync("Success", "Your payment has been recieved.", "Close");
	}
}