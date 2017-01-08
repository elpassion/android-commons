package com.elpassion.android.commons.recycler_example.group

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import com.elpassion.android.commons.recycler.basic.BasicList
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.User
import kotlinx.android.synthetic.main.github_item.view.*
import kotlinx.android.synthetic.main.recycler_view_with_action.*
import java.io.Closeable

class BasicContactsListActivity : AppCompatActivity() {

    var contacts: BasicContactsList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
            finish()
            return
        }
        setContentView(R.layout.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        contacts = BasicContactsList()
        recyclerView.adapter = basicAdapterWithLayoutAndBinder(contacts!!, R.layout.github_item) { holder, user ->
            with(holder.itemView) {
                userName.text = user.userName
                organization.text = user.organization
            }
        }
    }

    override fun onDestroy() {
        contacts?.close()
        super.onDestroy()
    }

    inner class BasicContactsList : BasicList<User>, Closeable {

        private val cursor = contentResolver.query(Contacts.CONTENT_URI, null, null, null, null)

        override fun get(key: Int): User {
            cursor.moveToPosition(key)
            return User(0L, cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY)), "C")
        }

        override val size: Int get() = cursor.count

        override fun close() = cursor.close()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BasicContactsListActivity::class.java))
        }

        const val DESCRIPTION = "Basic recycler with CONTACTS"
    }
}

