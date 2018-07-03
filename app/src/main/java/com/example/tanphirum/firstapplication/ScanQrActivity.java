package com.example.tanphirum.firstapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.tanphirum.firstapplication.fragment.dialog.CameraSelectorDialogFragment;
import com.example.tanphirum.firstapplication.fragment.dialog.FormatSelectorDialogFragment;
import com.example.tanphirum.firstapplication.fragment.dialog.MessageDialogFragment;
import com.example.tanphirum.firstapplication.utils.CustomFinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class ScanQrActivity extends AppCompatActivity implements MessageDialogFragment.MessageDialogListener,
        ZXingScannerView.ResultHandler, FormatSelectorDialogFragment.FormatSelectorDialogListener,
        CameraSelectorDialogFragment.CameraSelectorDialogListener, EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_TAKE_PHOTO = 121;

    @BindView(R.id.content_frame)
    ViewGroup contentFrame;
    private Menu mMenu;

    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
    private static final String CAMERA_ID = "CAMERA_ID";
    private static final String EXT_LIST_BARCODE = "EXT_LIST_BARCODE";

    protected ZXingScannerView mScannerView;
    protected boolean mFlash;
    protected boolean mAutoFocus;
    protected ArrayList<Integer> mSelectedIndices;
    protected int mCameraId = -1;
    protected boolean isCameraStart = false;
    protected ArrayList<String> listBarcode;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scan_qr);
        ButterKnife.bind(this);

        if (state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false);
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true);
            mSelectedIndices = state.getIntegerArrayList(SELECTED_FORMATS);
            mCameraId = state.getInt(CAMERA_ID, -1);
            listBarcode = state.getStringArrayList(EXT_LIST_BARCODE);
        } else {
            listBarcode = new ArrayList<>();
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;
        }
        mScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new CustomFinderView(context);
            }
        };

        setupFormats();
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        validateShowCamera();
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLASH_STATE, mFlash);
        outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus);
        outState.putIntegerArrayList(SELECTED_FORMATS, mSelectedIndices);
        outState.putInt(CAMERA_ID, mCameraId);
        outState.putStringArrayList(EXT_LIST_BARCODE, listBarcode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        MenuItem menuItem;

        if (mFlash) {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, R.string.flash_on);
        } else {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, R.string.flash_off);
        }
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);


        if (mAutoFocus) {
            menuItem = menu.add(Menu.NONE, R.id.menu_auto_focus, 0, R.string.auto_focus_on);
        } else {
            menuItem = menu.add(Menu.NONE, R.id.menu_auto_focus, 0, R.string.auto_focus_off);
        }
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);

        menuItem = menu.add(Menu.NONE, R.id.menu_formats, 0, R.string.formats);
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);

        menuItem = menu.add(Menu.NONE, R.id.menu_camera_selector, 0, R.string.select_camera);
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_flash:
                mFlash = !mFlash;
                if (mFlash) {
                    item.setTitle(R.string.flash_on);
                } else {
                    item.setTitle(R.string.flash_off);
                }
                mScannerView.setFlash(mFlash);
                return true;
            case R.id.menu_auto_focus:
                mAutoFocus = !mAutoFocus;
                if (mAutoFocus) {
                    item.setTitle(R.string.auto_focus_on);
                } else {
                    item.setTitle(R.string.auto_focus_off);
                }
                mScannerView.setAutoFocus(mAutoFocus);
                return true;
            case R.id.menu_formats:
                DialogFragment fragment = FormatSelectorDialogFragment.newInstance(this, mSelectedIndices);
                fragment.show(getSupportFragmentManager(), "format_selector");
                return true;
            case R.id.menu_camera_selector:
                mScannerView.stopCamera();
                DialogFragment cFragment = CameraSelectorDialogFragment.newInstance(this, mCameraId);
                cFragment.show(getSupportFragmentManager(), "camera_selector");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        try {/*
         *//**
         * This method need to show a yellow point on barcode
         * Transform resultPoints to View coordinates
         *//*
            final PointF[] transformedPoints =
                    transformToViewCoordinates(contentFrame, rawResult.getResultPoints());
            pointOverLay.setPoints(transformedPoints);*/
            mScannerView.resumeCameraPreview(this);
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
        }

        new AlertDialog.Builder(this).setMessage(rawResult.getText())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                }).show();

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // Resume the camera
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onFormatsSaved(ArrayList<Integer> selectedIndices) {
        mSelectedIndices = selectedIndices;
        setupFormats();
    }

    @Override
    public void onCameraSelected(int cameraId) {
        mCameraId = cameraId;
        mScannerView.startCamera(mCameraId);
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
    }

    @Override
    public void onPause() {
        mScannerView.stopCameraPreview();
        mScannerView.stopCamera();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mScannerView.stopCameraPreview();
        mScannerView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mScannerView.stopCameraPreview();
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();

        super.onDestroy();
    }

    public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
        if (mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<Integer>();
            for (int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for (int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }
        if (mScannerView != null)
            mScannerView.setFormats(formats);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        validateShowCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
            new AppSettingsDialog.Builder(this).build().show();
        else
            onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //easypersissions handle request result
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (hasCameraPermission(this))
                mScannerView.startCamera(mCameraId);
        }
    }

    @AfterPermissionGranted(REQUEST_TAKE_PHOTO)
    private void validateShowCamera() {
        if (hasCameraPermission(this)) {
            if (!isCameraStart) {
                isCameraStart = true;
                mScannerView.startCamera(mCameraId);
            }
        } else {
            EasyPermissions.requestPermissions(this,
                    getString(R.string.rationale_camera),
                    REQUEST_TAKE_PHOTO, android.Manifest.permission.CAMERA);
        }
    }

    private boolean hasCameraPermission(Context context) {
        return EasyPermissions.hasPermissions(context, Manifest.permission.CAMERA);
    }
}
