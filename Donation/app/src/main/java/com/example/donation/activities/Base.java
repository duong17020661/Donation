package com.example.donation.activities;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donation.R;
import com.example.donation.main.DonationApp;
import com.example.donation.models.Donation;

import java.util.ArrayList;
import java.util.List;


public class Base extends AppCompatActivity {

    public DonationApp app;
    public List<Donation> donations    = new ArrayList<Donation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
        donations = app.donations;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);
        MenuItem reset = menu.findItem(R.id.menuReset);

        if(donations.isEmpty() || app.totalDonated==0)
        {
            report.setEnabled(true);
            reset.setEnabled(false);
        }
        else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof Donate){
            donate.setVisible(false);
            if(!donations.isEmpty())
            {
                report.setVisible(true);
                reset.setEnabled(true);
            }
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(false);
        }

        return true;
    }

    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }

    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, Donate.class));
    }

    public void reset(MenuItem item)
    {

    }

}


