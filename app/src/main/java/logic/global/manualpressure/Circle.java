package logic.global.manualpressure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by zelenasova on 21.8.2015.
 */
public class Circle extends View {

    private float x, y;
    private float sphereRadius;
    final private Paint mPaint = new Paint();

    public Circle(Context context, float screenWidth, float sphereRadius,float sideMargin) {
        super(context);
        this.sphereRadius = sphereRadius;
        this.x= sphereRadius/2+sideMargin;
        this.y = screenWidth/2;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
    }

    float getCirleX () {
        return x;
    }
    void setCircleX (float x) {
        this.x=x;
    }
    float getCirleY () {
        return y;
    }
    void setCircleY (float y) {
        this.y=y;
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawCircle(x,y,sphereRadius,mPaint);
    }
}
