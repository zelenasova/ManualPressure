package triangle.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import logic.global.manualpressure.R;

public class ArticleControlsLayer extends FrameLayout {
	
	private Path triangle = new Path();
	TextView text;
	private float screenHeight;
	public ArticleControlsLayer(Context context) {
		super(context);
		
	//	addView(text, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
		 LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    li.inflate(R.layout.frame_detail, this, true);
		LinearLayout lin = (LinearLayout) findViewById(R.id.tags);
		lin.setGravity(Gravity.CENTER_HORIZONTAL);
		LayoutParams params1 = (LayoutParams) lin.getLayoutParams();
		DisplayMetrics metrics = getResources().getDisplayMetrics();
    	screenHeight = metrics.heightPixels;
    	params1.height=(int) (screenHeight/4/5*3);
		params1.width=(int) ((screenHeight/4/5*3)*6/5);
		
	//	addView(lin);
//		setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
	}



	public void setTriangleSize(int triangleSize) {
		triangle.reset();
		
		triangle.moveTo(0, 0);
		triangle.lineTo(triangleSize, 0);

		triangle.lineTo(0, triangleSize);
      
//		triangle.lineTo(a.x, a.y);
		triangle.close();
		
		invalidate();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.clipPath(triangle);
		super.dispatchDraw(canvas);
	}

}
