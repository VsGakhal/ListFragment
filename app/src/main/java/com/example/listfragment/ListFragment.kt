package com.example.listfragment

import android.os.Bundle
import android.app.Dialog
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import com.example.listfragment.databinding.ActivityNewBinding
import com.example.listfragment.databinding.ActivityUpdateBinding
import com.example.listfragment.databinding.FragmentListBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var arrayList : ArrayList<String> =ArrayList()
    lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentListBinding.inflate(layoutInflater)
        var adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, arrayList)
        arrayList.add("one")
        arrayList.add("two")
        arrayList.add("three")
        arrayList.add("four")

        binding.listView.adapter=adapter

        binding.fabButton.setOnClickListener{
            var dialogBinding = ActivityNewBinding.inflate(layoutInflater)
            var dialog= Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etNewItem.text.toString().isNullOrEmpty()) {
                    dialogBinding.etNewItem.setError("Enter Item")
                }
                else{
                    arrayList.add(dialogBinding.etNewItem.text.toString())
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }

            }
            dialog.show()
        }

        binding.listView.setOnItemClickListener {adapterView,view,i,l ->
            var dialogBinding = ActivityUpdateBinding.inflate(layoutInflater)
            var dialog= Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialogBinding.etUpdateItem.setText(arrayList[i])
            dialogBinding.btnUpdate.setOnClickListener{
                if(dialogBinding.etUpdateItem.text.toString().isNullOrEmpty()){
                    dialogBinding.etUpdateItem.setError("Enter Item")
                }
                else{
                    arrayList.set(i,dialogBinding.etUpdateItem.text.toString())
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}