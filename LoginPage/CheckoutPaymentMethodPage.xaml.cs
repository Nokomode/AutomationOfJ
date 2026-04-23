namespace LoginPage;

public partial class CheckoutPaymentMethodPage : ContentPage
{
	public CheckoutPaymentMethodPage()
	{
		InitializeComponent();
	}

    private void Button_Clicked(object sender, EventArgs e)
	{
		Navigation.PushAsync(new CheckoutPaymentContactInfoPage());
	}
}