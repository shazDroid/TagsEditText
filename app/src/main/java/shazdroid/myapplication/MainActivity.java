package shazdroid.myapplication;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.Collection;

import shazdroid.tagsedittext.ShazTagsEditText;

public class MainActivity extends AppCompatActivity
        implements ShazTagsEditText.TagsEditListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ShazTagsEditText mShazTagEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShazTagEditText = (ShazTagsEditText) findViewById(R.id.tagsEditText);
        mShazTagEditText.setHint("Enter names of fruits");
        mShazTagEditText.setTagsListener(this);
        mShazTagEditText.setTagsWithSpacesEnabled(true);
        mShazTagEditText.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.fruits)));
        mShazTagEditText.setThreshold(1);

        setButtonClickListener(R.id.btnChangeTags);
        setButtonClickListener(R.id.btnChangeBackground);
        setButtonClickListener(R.id.btnChangeColor);
        setButtonClickListener(R.id.btnChangeSize);
        setButtonClickListener(R.id.btnChangeDrawableLeft);
        setButtonClickListener(R.id.btnChangeDrawableRight);
        setButtonClickListener(R.id.btnChangeClosePadding);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mShazTagEditText.showDropDown();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangeTags: {
                //mTagsEditText.setTags("1", "2", "3");
                mShazTagEditText.setTags(new String[]{"1","2","3","4"});
                break;
            }
            case R.id.btnChangeBackground: {
                mShazTagEditText.setTagsBackground(R.drawable.square);
                break;
            }
            case R.id.btnChangeColor: {
                mShazTagEditText.setTagsTextColor(R.color.blackOlive);
                break;
            }
            case R.id.btnChangeSize: {
                mShazTagEditText.setTagsTextSize(R.dimen.larger_text_size);
                break;
            }
            case R.id.btnChangeDrawableLeft: {
                mShazTagEditText.setCloseDrawableLeft(R.drawable.dot);
                break;
            }
            case R.id.btnChangeDrawableRight: {
                mShazTagEditText.setCloseDrawableRight(R.drawable.tag_close);
                break;
            }
            case R.id.btnChangeClosePadding: {
                mShazTagEditText.setCloseDrawablePadding(R.dimen.larger_padding);
                break;
            }
        }
    }

    @Override
    public void onTagsChanged(Collection<String> tags) {
        Log.d(TAG, "Tags changed: ");
        Log.d(TAG, Arrays.toString(tags.toArray()));
    }

    @Override
    public void onEditingFinished() {
        Log.d(TAG,"OnEditing finished");
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(mTagsEditText.getWindowToken(), 0);
//        //mTagsEditText.clearFocus();
    }

    @Override
    public void onTagRemoved(int position) {
        Log.d(TAG, "onTagRemoved: " + position);
    }

    @Override
    public void onTagAdded(int position, String content) {
        Log.d(TAG, "onTagAdded: position : " + position + ", content : " + content);
    }

    private void setButtonClickListener(@IdRes int id) {
        findViewById(id).setOnClickListener(this);
    }

}
