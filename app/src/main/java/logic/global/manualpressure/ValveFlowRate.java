package logic.global.manualpressure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ValveFlowRate extends AppCompatActivity {

    Button btn_minus;
    Button btn_plus;
    int actualPosition = 0;
    ImageView cylinder;
    TextView text1;
    TextView text2;
    TextView text3;
    Animation fade_out1;
    Animation fade_out2;
    Animation fade_out3;
    Animation fade_in;
    String[] values = {"0", "¼" , "½" , "¾" , "1", "1½" , "2", "2½" , "3", "4", "5", "6" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valve_flow_rate);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        cylinder = (ImageView) findViewById(R.id.cylinder);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        btn_minus.setEnabled(false);
        text1.setText(values[actualPosition]);
        text2.setText(values[actualPosition+1]);
        text3.setText(values[actualPosition+2]);
        fade_out1 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fade_out2 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fade_out3 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        fade_out1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                text1.setText(values[actualPosition]);
                text1.startAnimation(fade_in);
            }
        });

        fade_out2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                text2.setText(values[actualPosition+1]);
                text2.startAnimation(fade_in);
            }
        });

        fade_out3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                text3.setText(values[actualPosition+2]);
                text3.startAnimation(fade_in);
            }
        });


        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition--;
                setImage();
                setTexts();
                if (actualPosition == 0) btn_minus.setEnabled(false);
                if(actualPosition < (values.length-3)) btn_plus.setEnabled(true);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition++;
                setImage();
                setTexts();
                if (actualPosition > 0) btn_minus.setEnabled(true);
                if(actualPosition == (values.length-3)) btn_plus.setEnabled(false);
            }
        });
    }

    private void setTexts(){
        text1.startAnimation(fade_out1);
        text2.startAnimation(fade_out2);
        text3.startAnimation(fade_out3);
    };

    private void setImage(){
        if ((actualPosition % 2) == 0){
            cylinder.setImageResource(R.drawable.img_valve);
        } else {
            cylinder.setImageResource(R.drawable.img_valve_2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manaul_pressure, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cirkular_seekbar) {
            Intent i = new Intent(this, CircularSeekBarActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }
        if (id == R.id.flow_rate) {
            Intent i = new Intent(this, ValveFlowRate.class);
            startActivity(i);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
