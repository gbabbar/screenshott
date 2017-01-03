/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.screenshottproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import github.nisrulz.screenshott.ScreenShott;

public class MainActivity extends AppCompatActivity {

  ImageView imageView;
  ImageButton capture_screenshot, capture_refresh, capture_save;
  Bitmap bitmap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    imageView = (ImageView) findViewById(R.id.imageView);

    capture_screenshot = (ImageButton) findViewById(R.id.capture_screenshot);
    capture_screenshot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        bitmap = ScreenShott.getInstance().takeScreenShotOfRootView(view);
        imageView.setImageBitmap(bitmap);
      }
    });

    capture_refresh = (ImageButton) findViewById(R.id.capture_refresh);
    capture_refresh.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bitmap placeholder =
            BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.placeholder);
        imageView.setImageBitmap(placeholder);
      }
    });

    capture_save = (ImageButton) findViewById(R.id.capture_save);
    capture_save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (bitmap != null) {
          // Save the screenshot
          ScreenShott.getInstance().saveScreenshotToPicturesFolder(bitmap, "my_screenshot");
        }
      }
    });
  }
}
