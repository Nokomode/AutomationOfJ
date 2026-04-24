namespace LoginPage;

public partial class CheckoutCardReviewPage : ContentPage
{
	public CheckoutCardReviewPage()
	{
		InitializeComponent();
	}

    private async void ConfirmButton_Clicked(object sender, EventArgs e)
	{
		await DisplayAlertAsync("Success", "Your payment has been recieved.", "Close");
	}
}