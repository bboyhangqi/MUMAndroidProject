package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.model.WallpaperModel
<<<<<<< HEAD
=======
import app.mumandroidproject.ui.adpter.CategoryAdapter
>>>>>>> 173a7ed60242d678f19f539d94d77f99f9888554
import app.mumandroidproject.ui.adpter.HotAdapter
import com.bumptech.glide.load.model.Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment() {
    var wallpaperList: MutableList<WallpaperItem> = mutableListOf()

    object HOLDER {
        val INSTANCE by lazy { HotFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        WallpaperModel.instance.getHotWallpaper(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                wallpaperList.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)

                    if (wallpaper != null) {
                        wallpaperList.add(wallpaper)
                    }
                    val hotAdapter = HotAdapter(wallpaperList)
                    rv.layoutManager = LinearLayoutManager(HotFragment.instance.context)
                    rv.adapter = hotAdapter
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }


    fun getWallpaperItems(){
        WallpaperModel.instance.getAllWallpapers()
        //sort
    }

}
