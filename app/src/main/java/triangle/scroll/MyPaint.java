package triangle.scroll;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class MyPaint extends View  implements GestureDetector.OnGestureListener {
	
	private int defaultTriangleSize;
	private int triangleSize;
	private ArticleControlsLayer controls;	
	private GestureDetectorCompat gDetector;
	private ImageView img;
	ArticleThumbnailFrame parent;
	private float xFrame;
	private float yFrame;
	private float koef=0.5f;
	int imgMargin;
	int imgWidth;

	
	public MyPaint(Context context, ArticleControlsLayer controls, ImageView img, int imgMargin, int imgWidth) {
			super(context);
			gDetector = new GestureDetectorCompat(context, this);
			this.controls = controls;
		 	DisplayMetrics metrics = getResources().getDisplayMetrics();
	    	float screenWidth = metrics.widthPixels;
	    	float screenHeight= metrics.widthPixels;
	    	this.defaultTriangleSize= (int)(screenHeight/9);
	    	triangleSize=defaultTriangleSize;
	    	this.img=img;
	    	this.imgMargin=imgMargin;
	    	this.imgWidth=imgWidth;
	    //	this.setLayoutParams( new FrameLayout.LayoutParams(defaultTriangleSize, defaultTriangleSize));
	    	this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    	
	}
	
	public int getTriangleSize() {
		return triangleSize;
	}
	
	public void setTriangleSize(int triangleSize) {
		if (triangleSize != this.triangleSize) {
			this.triangleSize = triangleSize;
			LayoutParams par = (LayoutParams) img.getLayoutParams();
			float position=triangleSize;
			koef =(position-defaultTriangleSize)/(xFrame+yFrame-defaultTriangleSize);
			if (koef>1) koef = 1;
			if (koef<0) koef=0;
			//controls.setText(koef+"");
			par.setMargins(((int) (koef*(xFrame-2*imgMargin-imgWidth))+imgMargin), (int) (koef*(yFrame-2*imgMargin-imgWidth))+imgMargin, 0, 0);
			img.setLayoutParams(par);
			invalidate();		
			if (controls.getVisibility() == View.VISIBLE) {
				controls.setTriangleSize(triangleSize);
			}
			               
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
	    super.onDraw(canvas);	   
	    canvas.save(); 
	    Paint paint = new Paint();	      
	    paint.setColor(android.graphics.Color.parseColor("#58424d"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(false);
        Point a = new Point(0, 0);
        Point b = new Point(0, triangleSize);
        Point c = new Point(triangleSize,0);
        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);    
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, paint);     
	    canvas.restore();
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent me) {
		parent = (ArticleThumbnailFrame) getParent(); 
		xFrame=parent.getActualWidth();
		yFrame=parent.getActualHeight();
		switch (me.getAction()) {
			case MotionEvent.ACTION_DOWN:
				System.out.println("x:"+me.getX());
				System.out.println("y:"+me.getY());
				this.controls.setVisibility(View.VISIBLE);
				break;
			case MotionEvent.ACTION_UP:
				
				
				if (me.getX() > parent.getActualWidth() / 2 || me.getY() > parent.getActualHeight() / 2) {
					//this.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//					this.setTriangleSize(parent.getActualWidth() + parent.getActualHeight());
					ObjectAnimator triangleAnimator = ObjectAnimator.ofInt(this, "triangleSize",
							parent.getActualWidth() + parent.getActualHeight());
					triangleAnimator.setDuration(500);
					triangleAnimator.start();
				} else {
					ObjectAnimator triangleAnimator = ObjectAnimator.ofInt(this, "triangleSize", defaultTriangleSize);
					triangleAnimator.addListener(new AnimatorListener() {
						@Override
						public void onAnimationEnd(Animator animation) {
						//	MyPaint.this.setLayoutParams(new FrameLayout.LayoutParams(defaultTriangleSize, defaultTriangleSize));
							MyPaint.this.controls.setVisibility(View.INVISIBLE);
						}

						@Override
						public void onAnimationCancel(Animator animation) {
						}

						@Override
						public void onAnimationRepeat(Animator animation) {
						}

						@Override
						public void onAnimationStart(Animator animation) {
						}
						
					});
					triangleAnimator.setDuration(500);
					triangleAnimator.start();
					
					
				}
				break;
		}
        
		return gDetector.onTouchEvent(me);
		

    }
	 

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return true;
	}
	@Override
	public void onLongPress(MotionEvent e) {
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		
		setTriangleSize((int) (e2.getX() + e2.getY()));
		return true;
	}
	@Override
	public void onShowPress(MotionEvent e) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	
	
}
