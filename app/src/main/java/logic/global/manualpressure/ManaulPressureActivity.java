package logic.global.manualpressure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class ManaulPressureActivity extends Activity {

    private FrameLayout frame;
    float fingerX;
    float fingerY;
    Circle circle;
    private static final int MIN_DELTA = 1;
    private float screenWidth;
    private  float strokeRadius = 20;
    private float sphereRadius = 30;
    private float circleRadius;
    private float delta; //delta between coordinate systems
    private float sideMargin = 30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manaul_pressure);
        frame = (FrameLayout) findViewById(R.id.plocha);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        circleRadius=screenWidth/2;
        delta = screenWidth/2;
        circleRadius = screenWidth/2-strokeRadius;
        initViews();
        //priemer 680 (720-40)
        //stred kruznice je v 360 360 (340+40/2)

        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        System.out.println(event.getX());
                        System.out.println(event.getY());
                        break;
                    }

                    case MotionEvent.ACTION_UP: {

                        break;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        if ((Math.abs(circle.getCirleX() - event.getX()) > MIN_DELTA) || Math.abs(circle.getCirleY() - event.getY()) > MIN_DELTA) {

                            if ((Math.abs(circle.getCirleX()-event.getX())<=60) && (Math.abs(circle.getCirleY() - event.getY())<=60)){
                                double x = event.getX()-delta;
                                System.out.println("x:"+x);
                                double y = Math.sqrt(Math.pow(circleRadius, 2)-Math.pow(x, 2));
                                System.out.println("y:" + y);
                                circle.setCircleX((float) x + delta);
                                circle.setCircleY(delta-(float) y);
                                circle.invalidate();
                            }

                        }
                        break;
                    }
                    default:

                }
                return true;
            }

        });
    }


    private void initViews() {
        circle = new Circle(getApplicationContext(), screenWidth, sphereRadius, sideMargin);
        frame.addView(new SemiCircleDrawable(getApplicationContext(),screenWidth, strokeRadius, sideMargin));
        //frame.addView(new Circle(getApplicationContext(), 20, 360));
        frame.addView(circle);


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
            Intent i = new Intent(this, ManaulPressureActivity.class);
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
