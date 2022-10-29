package com.example.testgradle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactListDialog extends Dialog implements OnAdapterItemClickListener {

    private OnAdapterItemClickListener callback;

    public ContactListDialog(@NonNull Context context, OnAdapterItemClickListener callback) {
        super(context);
        this.callback = callback;
    }

    public ContactListDialog(@NonNull Context context) {
        super(context);
    }

    public ContactListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ContactListDialog(@NonNull Context context, RecyclerView recycler_view, ArrayList<ContactModel> arrayList) {
        super(context);
        this.recycler_view = recycler_view;
        this.arrayList = arrayList;

    }

    protected ContactListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    RecyclerView recycler_view;
    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        recycler_view = findViewById(R.id.recycler_view);
        checkPermission();

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            getContactList();
        }
    }

    private void getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, sort);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID
                ));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME
                ));
                Uri uriphone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = getContext().getContentResolver().query(
                        uriphone, null, selection, new String[]{id}, null
                );
                if (phoneCursor.moveToNext()) {
                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    ContactModel model = new ContactModel();

                    model.setName(name);

                    model.setNumber(number);

                    arrayList.add(model);

                    phoneCursor.close();
                }
            }
            cursor.close();
        }
        recycler_view.setLayoutManager((new LinearLayoutManager(getContext())));

        adapter = new MainAdapter(arrayList, this);

        recycler_view.setAdapter(adapter);
    }

    public void onAdapterItemClickListener(ContactModel contact) {
        callback.onAdapterItemClickListener(contact);
        dismiss();
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}


