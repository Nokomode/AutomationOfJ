namespace LoginPage;

public partial class CheckoutCashPaymentContactInfoPage : ContentPage
{
	public CheckoutCashPaymentContactInfoPage()
	{
		InitializeComponent();
	}

    private void Button_Clicked(object sender, EventArgs e)
	{
		Navigation.PushAsync(new CheckoutCardReviewPage());
	}
}