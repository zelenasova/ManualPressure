package triangle.scroll;


import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import logic.global.manualpressure.R;

public class ArticleThumbnailFrame extends FrameLayout {

	Context context;
	private int actualWidth;
	ArticleControlsLayer controlsLayer;
	public int getActualWidth() {
		return actualWidth;
	}
	
	private int actualHeight;
	public int getActualHeight() {
		return actualHeight;
	}

	public ArticleThumbnailFrame(Context context ) {
		super(context);

		this.context=context;

		DisplayMetrics metrics = getResources().getDisplayMetrics();

		ImageView cornerImg = new ImageView(context);

		cornerImg.setImageResource(R.drawable.corner);
		
		

		int imgWidth = (int)(metrics.widthPixels/32);
    	int imgMargin = (int)(metrics.widthPixels/32/2);
		RelativeLayout rel = new RelativeLayout(context);
		RelativeLayout rel2 = new RelativeLayout(context); 
		LinearLayout lin = new LinearLayout(context);

		float w = metrics.widthPixels/14*13/5/3;
		float h = w/130*36;
		LinearLayout.LayoutParams linPar = new LinearLayout.LayoutParams((int)w,(int) h);
		lin.setLayoutParams(linPar);
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
	//	RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(w, 50);
		rel.setPadding(metrics.widthPixels/14*13/5/8, 0, metrics.widthPixels/14*13/5/20, metrics.widthPixels/14*13/5/20);

		
		params3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		rel.addView(lin,params1);
		rel2.setBackgroundColor(Color.WHITE);




		controlsLayer = new ArticleControlsLayer(context);
		controlsLayer.setVisibility(View.INVISIBLE);
		
		
	
		LayoutParams cornerParams = new LayoutParams(imgWidth, imgWidth);
		cornerParams.setMargins(imgMargin, imgMargin, 0, 0);
		cornerImg.setLayoutParams(cornerParams);
		MyPaint viewPaint = new MyPaint(context, controlsLayer,cornerImg,imgMargin,imgWidth);
	


		this.addView(rel,params1);
		this.addView(viewPaint);
		this.addView(controlsLayer, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		this.addView(cornerImg);
	}
	
	


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);		
		actualWidth = MeasureSpec.getSize(widthMeasureSpec);
		actualHeight = MeasureSpec.getSize(heightMeasureSpec);
	}
	

	
	
	

	
}
