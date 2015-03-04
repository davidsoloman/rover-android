package co.roverlabs.sdk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.roverlabs.sdk.R;
import co.roverlabs.sdk.managers.RoverVisitManager;
import co.roverlabs.sdk.models.RoverVisit;

/**
 * Created by SherryYang on 2015-02-04.
 */
public class CardActivity extends Activity {
    
    private static final String TAG = CardActivity.class.getName();
    private TextView mTitleView;
    private TextView mMessageView;
    private Button mButton;
    private RoverVisit mLatestVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        
        mTitleView = (TextView)findViewById(R.id.cardTitle);
        mMessageView = (TextView)findViewById(R.id.cardMessage);
        mButton = (Button)findViewById(R.id.cardButton);
        
        mLatestVisit = RoverVisitManager.getInstance(this.getApplicationContext()).getLatestVisit();
        mTitleView.setText(mLatestVisit.getTouchPoints().get(0).getCards().get(0).getTitle());
        mMessageView.setText("This is card ID " + mLatestVisit.getTouchPoints().get(0).getCards().get(0).getId());
        mButton.setText("OK");
        
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        
        super.onResume();
        mLatestVisit = RoverVisitManager.getInstance(this.getApplicationContext()).getLatestVisit();
    }
}