
package com.example.amartyamondal.justjava;
import java.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean haswhippedcream;
    boolean haschocolate;
    int price_calculated=0;
    String pricemessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (quantity!=0) {

            boolean doing_reset = false;
            TotalPrice(doing_reset);

            ThankU("Thank You");
        }

    }

    /**
     * A code for the increment function
     */
    public void increment(View view)

    {
        quantity = quantity + 1;
        display();
    }

    /**
     * A code for the decrement function
     */
    public void decrement(View view) {
        if (quantity>0){
        quantity = quantity - 1;
        display();
    }
    }
    public void counting_extra(View view){
        displayPrice();
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
        displayPrice();
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        price_calculated = quantity * 5;
        CheckBox whippedcream = (CheckBox) findViewById(R.id.checkwhip);
        haswhippedcream=whippedcream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.checkchocolate);
        haschocolate=chocolate.isChecked();
        if (haswhippedcream==true){
            price_calculated+=12;
        }
        if (haschocolate==true){
            price_calculated+=7;
        }
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price_calculated));
    }

    /**
     * This method displays the Total price on the screen.
     */
    public void TotalPrice(boolean doing_reset) {
        EditText name_inp= (EditText) findViewById(R.id.name_input);
        EditText email_inp=(EditText) findViewById(R.id.email_input);
        EditText phone_inp=(EditText) findViewById(R.id.phone_input) ;
        CheckBox whippedcream = (CheckBox) findViewById(R.id.checkwhip);
        CheckBox chocolate = (CheckBox) findViewById(R.id.checkchocolate);
        displayPrice();

        if (doing_reset==false) {
            haswhippedcream = whippedcream.isChecked();
            haschocolate = chocolate.isChecked();
            pricemessage = "Name: " + name_inp.getText();
            pricemessage+="\nEmail: "+ email_inp.getText();
            pricemessage+="\nPhone: " + phone_inp.getText();
            pricemessage += "\nQuantity: " + quantity;
            String Price=NumberFormat.getCurrencyInstance().format(price_calculated) ;
            pricemessage += "\nTotal Price: " + Price;
            pricemessage += "\nAdded whipped cream: " + haswhippedcream;
            pricemessage += "\nAdded chocolate:  " + haschocolate;
            TextView total_price = (TextView) findViewById(R.id.total_price);
            total_price.setText(pricemessage);


        }
        else{
            TextView total_price = (TextView) findViewById(R.id.total_price);
            total_price.setText("");
            name_inp.setText("");
            email_inp.setText("");
            phone_inp.setText("");
            whippedcream.setChecked(false);
            chocolate.setChecked(false);
        }
    }
    public void confirm_order(View view){
        boolean doing_reset=false;
        TotalPrice(doing_reset);
        EditText name_inp= (EditText) findViewById(R.id.name_input);
        EditText email_inp=(EditText) findViewById(R.id.email_input);
        String subject= "Order from "+ name_inp.getText();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_EMAIL, email_inp.getText());
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT, pricemessage);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    /**
     * This method says thank u to the user for ordering item
     */
    public void ThankU(String ntd) {
        TextView thnk_u = (TextView) findViewById(R.id.thnk_u);
        thnk_u.setText("" + ntd);
    }
    /**
     * This method resets all the parameters to their initial value
     */
    public void RESET(View view){
        price_calculated=0;
        quantity=0;
        pricemessage="";
        boolean doing_reset =true;
        haswhippedcream = false;
        haschocolate=false;
        TotalPrice(doing_reset);
        ThankU("");
        display();

    }
}