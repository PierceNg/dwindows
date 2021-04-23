package org.dbsoft.dwindows.dwtest

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Half.toFloat
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class DWindows : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val m = packageManager
        var s = packageName
        val p = m.getPackageInfo(s!!, 0)
        s = p.applicationInfo.dataDir

        // Initialize the Dynamic Windows code...
        // This will start a new thread that calls the app's dwmain()
        dwindowsInit(s)
    }
    /*
     * These are the Android calls to actually create the UI...
     * forwarded from the C Dynamic Windows API
     */
    fun windowNew(title: String, style: Int): LinearLayout
    {
        var windowLayout: LinearLayout = LinearLayout(this)
        setContentView(windowLayout)
        // For now we just return our DWindows' main activity layout...
        // in the future, later calls should create new activities
        return windowLayout
    }

    fun boxNew(type: Int, pad: Int): LinearLayout
    {
        val box = LinearLayout(this)
        box.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        if (type > 0) {
            box.orientation = LinearLayout.VERTICAL
        } else {
            box.orientation = LinearLayout.HORIZONTAL
        }
        return box
    }

    fun boxPack(box: LinearLayout, item: View, index: Int, width: Int, height: Int, hsize: Int, vsize: Int, pad: Int)
    {
        var w: Int = LinearLayout.LayoutParams.WRAP_CONTENT
        var h: Int = LinearLayout.LayoutParams.WRAP_CONTENT

        if(item is LinearLayout) {
            if (vsize > 0) {
                h = LinearLayout.LayoutParams.MATCH_PARENT
            }
            if (hsize > 0) {
                w = LinearLayout.LayoutParams.MATCH_PARENT
            }
        }
        var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(w, h)

        if(item !is LinearLayout && (width != -1 || height != -1)) {
            item.measure(0,0)
            if (width > 0) {
                w = width
            } else if(width == -1) {
                w = item.getMeasuredWidth()
            }
            if (height > 0) {
                h = height
            } else if(height == -1) {
                h = item.getMeasuredHeight()
            }
        }
        if(vsize > 0) {
            if(w > 0) {
                params.weight = w.toFloat()
            } else {
                params.weight = 1F
            }
        }
        if(hsize > 0) {
            if(h > 0) {
                params.weight = h.toFloat()
            } else {
                params.weight = 1F
            }
        }
        if(pad > 0) {
            params.setMargins(pad, pad, pad, pad)
        }
        var grav: Int = Gravity.CLIP_HORIZONTAL or Gravity.CLIP_VERTICAL
        if(hsize > 0 && vsize > 0) {
            params.gravity = Gravity.FILL or grav
        } else if(hsize > 0) {
            params.gravity = Gravity.FILL_HORIZONTAL or grav
        } else if(vsize > 0) {
            params.gravity = Gravity.FILL_VERTICAL or grav
        }
        item.layoutParams = params
        box.addView(item)
    }

    fun boxUnpack(item: View)
    {
        var box: LinearLayout = item.parent as LinearLayout
        box.removeView(item)
    }

    fun buttonNew(text: String, cid: Int): Button
    {
        val button = Button(this)
        button.text = text
        return button
    }

    fun entryfieldNew(text: String, cid: Int, password: Int): EditText
    {
        val entryfield = EditText(this)
        if(password > 0) {
            entryfield.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        entryfield.setText(text)
        return entryfield
    }

    fun radioButtonNew(text: String, cid: Int): RadioButton
    {
        val radiobutton = RadioButton(this)
        radiobutton.text = text
        return radiobutton
    }

    fun checkboxNew(text: String, cid: Int): CheckBox
    {
        val checkbox = CheckBox(this)
        checkbox.text = text
        return checkbox
    }

    fun textNew(text: String, cid: Int, status: Int): TextView
    {
        val textview = TextView(this)
        textview.text = text
        return textview
    }

    /*
     * Native methods that are implemented by the 'dwindows' native library,
     * which is packaged with this application.
     */
    external fun dwindowsInit(dataDir: String): String

    companion object
    {
        // Used to load the 'dwindows' library on application startup.
        init
        {
            System.loadLibrary("dwindows")
        }
    }
}