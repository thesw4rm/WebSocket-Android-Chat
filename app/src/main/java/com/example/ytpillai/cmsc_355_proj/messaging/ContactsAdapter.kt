package com.example.ytpillai.cmsc_355_proj.messaging

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.ConversationActivity
import com.example.ytpillai.cmsc_355_proj.R
import kotlinx.android.synthetic.main.contact_list.view.*

private const val TAG = "ContactsAdapter"


class ContactsAdapter (val context: Context) : RecyclerView.Adapter<ContactViewHolder>() {
    private val contacts: ArrayList<Contact> = ArrayList()


    fun addContact(contact: Contact){
        contacts.add(contact)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        return NewContactViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_list, parent, false))

    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)

    }

    }

    class NewContactViewHolder (view: View) : ContactViewHolder(view) {
        private var contactNickname: TextView = view.nicknameContactList
        private var contactIP: TextView = view.ipAddressContactList
        private var timeText: TextView = view.timeStampContactList

        override fun bind(contact: Contact) {
            contactNickname.text = App.nickname
            contactIP.text = App.ip
            timeText.text = DateUtils.fromMillisToTimeString(contact.time)
        }

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, ConversationActivity::class.java)
                view.context.startActivity(intent)
            }
        }
    }

open class ContactViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(contact:Contact) {}
}
