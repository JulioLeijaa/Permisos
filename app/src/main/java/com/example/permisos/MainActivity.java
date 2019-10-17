package com.example.permisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            verifyPermission();
        }
    }

    public void llamar(){
        it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:871-119-1974"));
        startActivity(it);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.llamar:
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                llamar();
                break;
            case R.id.navegar:
                Intent t1 = new Intent(Intent.ACTION_VIEW);
                t1.setData(Uri.parse("http://www.ramiro174.com"));
                startActivity(t1);
                break;
            case R.id.camara:
                Toast.makeText(this, "Camara prro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.archivos:
                Toast.makeText(this, "Archivos prro", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void verifyPermission() {

        int permsRequestCode = 100;
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE,Manifest.permission.RECORD_AUDIO};
        int accessFinePermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        int accessCoarsePermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        int cameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
        int phonePermission= checkSelfPermission(Manifest.permission.CALL_PHONE);

        if (  cameraPermission == PackageManager.PERMISSION_GRANTED && accessFinePermission == PackageManager.PERMISSION_GRANTED && accessCoarsePermission == PackageManager.PERMISSION_GRANTED && phonePermission==PackageManager.PERMISSION_GRANTED) {
            //se realiza metodo si es necesario...
        } else {
            requestPermissions(perms, permsRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                }
                break;
        }
    }
}
