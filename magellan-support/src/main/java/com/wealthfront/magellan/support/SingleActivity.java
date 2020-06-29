package com.wealthfront.magellan.support;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import com.wealthfront.magellan.Navigator;

public abstract class SingleActivity extends AppCompatActivity {

  private static Navigator navigator;

  protected abstract Navigator createNavigator();

  public static Navigator getNavigator() {
    return navigator;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (navigator == null) {
      navigator = createNavigator();
    }
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    navigator.onCreate(this, savedInstanceState);
    super.onPostCreate(savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    navigator.onSaveInstanceState(outState);
    super.onSaveInstanceState(outState);
  }

  @Override
  protected void onResume() {
    super.onResume();
    navigator.onResume(this);
  }

  @Override
  protected void onPause() {
    navigator.onPause(this);
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    navigator.onDestroy(this);
    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
    if (!navigator.handleBack()) {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    navigator.onCreateOptionsMenu(menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    navigator.onPrepareOptionsMenu(menu);
    return super.onPrepareOptionsMenu(menu);
  }

}
