namespace LoginPage;

public partial class CheckoutCardPaymentContactInfoPage : ContentPage
{
	public CheckoutCardPaymentContactInfoPage()
	{
		InitializeComponent();
	}

	private void Button_Clicked(Object sender, EventArgs e)
	{
		Navigation.PushAsync(new CheckoutCardReviewPage());
	}
}