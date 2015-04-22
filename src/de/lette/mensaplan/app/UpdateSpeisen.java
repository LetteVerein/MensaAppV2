package de.lette.mensaplan.app;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class UpdateSpeisen extends AsyncTask<Void, Void, Void> {
	private Context mCon;
	
	public UpdateSpeisen(Context con) {
		mCon = con;
	}
	
	@Override
    protected Void doInBackground(Void... params) {
        try {
            // Set a time to simulate a long update process.
            Thread.sleep(4000);
             
            return null;
             
        } catch (Exception e) {
            return null;
        }
    }
     
    @Override
    protected void onPostExecute(Void params) {
        // Give some feedback on the UI.
        Toast.makeText(mCon, "Finished complex background function!", 
                Toast.LENGTH_LONG).show();
         
        // Change the menu back
        ((MainActivity) mCon).resetUpdating();
    }
	
}