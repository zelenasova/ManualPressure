package logic.global.manualpressure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by zelenasova on 21.8.2015.
 */
public class SemiCircleDrawable extends View {
    private Paint paint;
    private RectF rectF;
    private float strokeRadius;
    private float screenWidth;
    private float sideMargin;

    public SemiCircleDrawable(Context context,float screenWidth, float strokeRadius, float sideMargin) {
        super(context);
        this.strokeRadius = strokeRadius;
        this.screenWidth = screenWidth;
        this.sideMargin=sideMargin;
        paint = new Paint();
        paint.setStrokeWidth(strokeRadius*2);
        paint.setStyle(Paint.Style.STROKE);
        rectF = new RectF();
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.save();

        paint.setColor(Color.RED);
        rectF.set(strokeRadius,strokeRadius,screenWidth-strokeRadius,screenWidth-strokeRadius);
        canvas.drawArc(rectF, -180, 30, false, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, -150, 40, false, paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, -110, 110, false, paint);
    }

}
