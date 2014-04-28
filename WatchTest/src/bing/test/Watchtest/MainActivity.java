package bing.test.Watchtest;

//import dev.coinwatch.coin.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import java.net.*;
import java.io.*;
import java.awt.*;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class MainActivity extends ActionBarActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		//setupImage();
		setupButton();
		
	}
	private void setupImage() throws IOException {
		// TODO Auto-generated method stub
		/*
		Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
		ByteArrayOutputStream out = QRCode.from("bitcoin:1DQhYSp7enJTnVqSLSWmNm4dwi2CQWUJL9?amount=6969&label=haerpaderp").to(ImageType.PNG).stream();
		try {
			File folder = new File(Environment.getExternalStorageDirectory() + "/coinwatchtest");
			boolean success = true;
			if(!folder.exists())
			{
				success = folder.mkdir();
			}
			FileOutputStream fout = new FileOutputStream(new File("/coinwatchtemp/qrtest.jpg"));
			fout.write(out.toByteArray());
			fout.flush();
			fout.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		File sdcard = Environment.getExternalStorageDirectory();
		File directory = new File(sdcard.getAbsolutePath() + "/coinwatchtemp");
		File file = new File(directory, "test");
		FileInputStream streamIn = new FileInputStream(file);
		//String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "/coinwatchtest/test";
		ImageView image = (ImageView)findViewById(R.id.imageView1);
		Bitmap BMPImage = BitmapFactory.decodeStream(streamIn);
		streamIn.close();
		image.setImageBitmap(BMPImage);
	}
	private void setupButton() {
		// TODO Auto-generated method stub
		Button testButton = (Button)findViewById(R.id.button1);
		testButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Log.i("Clicking Demo Button","Oh you touch me just right!");
				//Toast.makeText(MainActivity.this, "Oh Right there, don't stop!", Toast.LENGTH_LONG).show();
				String amount = "Shits Broke Yo";
				try {
					amount = getBalance(((EditText)findViewById(R.id.editText1)).getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(amount);
				Toast.makeText(MainActivity.this, amount, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private String getBalance(String address) throws Exception
	{
		String balance;
		URL blockchain = new URL("http://blockchain.info/q/addressbalance/" + ((EditText)findViewById(R.id.editText1)).getText().toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(blockchain.openStream()));
		String inputLine = in.readLine();
		System.out.println("inputLine: " + inputLine);
		if(inputLine.equals("Checksum does not validate"))
		{
			balance = "Invalid Address";
		}
		else
		{
			balance = inputLine;
		}
		return balance;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}*/

}
