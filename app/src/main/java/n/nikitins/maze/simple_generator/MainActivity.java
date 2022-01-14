package n.nikitins.maze.simple_generator;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);

		SharedPreferences prefs = getSharedPreferences("mazePrefs", Activity.MODE_PRIVATE);
		
		Button quickGame = (Button) findViewById(R.id.quickGame);
		final AlertDialog.Builder quickGameBuilder = new AlertDialog.Builder(this);
		
        quickGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				quickGameBuilder.setTitle("Choose Difficulty")
    			.setItems(R.array.difficulty_options, new DialogInterface.OnClickListener() {
                
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//finish();
    					Intent intent = new Intent(getApplicationContext(), GameActivity.class);

    					switch(which) {
    					
    					case 0: //sandbox
    						intent.putExtra("level", 1);
    						intent.putExtra("mode", 0);
    						startActivity(intent);
    						break;
    						
    					case 1://normal
    						intent.putExtra("level", 2);
    						intent.putExtra("mode", 0);
    						startActivity(intent);
    						break;	
    					case 2://hard
    						intent.putExtra("level", 3);
    						intent.putExtra("mode", 0);
    						startActivity(intent);
    						break;	
    					case 3://insane
    						intent.putExtra("level", 4);
    						intent.putExtra("mode", 0);
    						startActivity(intent);
    						break;	
    					case 4://impossible
    						intent.putExtra("level", 5);
    						intent.putExtra("mode", 0);
    						startActivity(intent);
    						break;
    					}
    				}
    			});
				
				AlertDialog dialog = quickGameBuilder.create();
    			dialog.setCancelable(true);
    			dialog.show();
				
			}
		});
        
        
        Button practiceGame = (Button) findViewById(R.id.practiceGame);
		final AlertDialog.Builder practiceGameBuilder = new AlertDialog.Builder(this);
		
        practiceGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				practiceGameBuilder.setTitle("Choose Difficulty")
    			.setItems(R.array.difficulty_options, new DialogInterface.OnClickListener() {
                
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//finish();
    					Intent intent = new Intent(getApplicationContext(), GameActivity.class);

    					switch(which) {
    					
    					case 0: //sandbox
    						intent.putExtra("level", 1);
    						intent.putExtra("mode", -1);
    						startActivity(intent);
    						break;
    						
    					case 1://normal
    						intent.putExtra("level", 2);
    						intent.putExtra("mode", -1);
    						startActivity(intent);
    						break;	
    					case 2://hard
    						intent.putExtra("level", 3);
    						intent.putExtra("mode", -1);
    						startActivity(intent);
    						break;	
    					case 3://insane
    						intent.putExtra("level", 4);
    						intent.putExtra("mode", -1);
    						startActivity(intent);
    						break;	
    					case 4://impossible
    						intent.putExtra("level", 5);
    						intent.putExtra("mode", -1);
    						startActivity(intent);
    						break;
    					}
    				}
    			});
				
				AlertDialog dialog = practiceGameBuilder.create();
    			dialog.setCancelable(true);
    			dialog.show();
				
			}
		});
        
        Button records = (Button) findViewById(R.id.records);
        records.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(),RecordsTabFragmentActivity.class);
				startActivity(intent);
				
			}
		});
        
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(),Tutorial.class);
				startActivity(intent);
				
			}
		});
		
		final SharedPreferences mPreferences = getSharedPreferences("mazePrefs", Activity.MODE_PRIVATE);
	}
}
