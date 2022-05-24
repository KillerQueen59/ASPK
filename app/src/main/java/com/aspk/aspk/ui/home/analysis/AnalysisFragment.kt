package com.aspk.aspk.ui.home.analysis

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.databinding.FragmentAnalysisBinding
import com.aspk.aspk.ui.home.HomeFragmentDirections
import com.opencsv.CSVReader
import splitties.toast.toast
import java.io.*


class AnalysisFragment: Fragment() {

    lateinit var binding: FragmentAnalysisBinding
    private val MY_REQUEST_CODE_PERMISSION = 1000
    private val MY_RESULT_CODE_FILECHOOSER = 2000
    private val LOG_TAG = "AndroidExample"
    lateinit var hasilAkhir: MainData
    private val homeAuthController: NavController? by lazy { activity?.findNavController(R.id.fcv_home) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalysisBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hasilAkhir = MainData(arrayListOf())
        handleClick()
    }


    private fun handleClick(){
        with(binding){
            addData.setOnClickListener {
                askPermissionAndBrowseFile()
            }
            btnAnalysis.setOnClickListener {
                if (hasilAkhir.data!!.size != 0)
                    goToGraph(hasilAkhir)
                else
                    toast("Up File ")
            }
        }
    }

    private fun askPermissionAndBrowseFile() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Level 23

            val permisson = ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (permisson != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_REQUEST_CODE_PERMISSION
                )
                return
            }
        }
        doBrowseFile()
    }

    private fun doBrowseFile() {
        var chooseFileIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooseFileIntent.type = "*/*"
        chooseFileIntent.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFileIntent = Intent.createChooser(chooseFileIntent, "Choose a file")
        startActivityForResult(chooseFileIntent, MY_RESULT_CODE_FILECHOOSER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            MY_RESULT_CODE_FILECHOOSER -> if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val result = readCSV(data.data!!)
                    val finalData = arrayListOf<FoodData>()
                    result.removeAt(0)
                    result.map {
                        val dataData = it.split(",")
                        finalData.add(FoodData(dataData[0].split(' ')[0].split("/")[1],dataData[2], dataData[3].toLong()))
                    }

                    val hasil = finalData.groupBy { it.date } . mapValues { values-> values.value.groupBy { it.itemDesc }.mapValues { sum-> sum.value.sumBy { it.quantity.toInt()} } }
                    val hasilJanuari =  hasil["1"]?.mapValues { result-> result.value }
                    val entriesJanuari: List<FoodDataLeast> = hasilJanuari?.toList()?.map { FoodDataLeast(it.first,
                        it.second.toLong()
                    ) }!!

                    val hasilFebruari =  hasil["2"]?.mapValues { result-> result.value }
                    val entriesFeruari: List<FoodDataLeast> = hasilFebruari?.toList()?.map { FoodDataLeast(it.first,
                        it.second.toLong()
                    ) }!!

                    val hasilMaret =  hasil["2"]?.mapValues { result-> result.value }
                    val entriesMaret: List<FoodDataLeast> = hasilMaret?.toList()?.map { FoodDataLeast(it.first,
                        it.second.toLong()
                    ) }!!

                    val hasilByBulan = arrayListOf<List<FoodDataLeast>>()
                    hasilByBulan.add(entriesJanuari.slice(1..5).toTypedArray().toCollection(ArrayList()))
                    hasilByBulan.add(entriesFeruari.slice(1..5).toTypedArray().toCollection(ArrayList()))
                    hasilByBulan.add(entriesMaret.slice(1..5).toTypedArray().toCollection(ArrayList()))
                    hasilAkhir = MainData(hasilByBulan)

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    @Throws(IOException::class)
    fun readCSV(uri: Uri): ArrayList<String> {
        val csvFile = activity?.contentResolver?.openInputStream(uri)
        val isr = InputStreamReader(csvFile)
        return BufferedReader(isr).readLines().toTypedArray().toCollection(ArrayList())
    }

    private fun goToGraph(data: MainData){
        val direction = AnalysisFragmentDirections.actionAnalysisFragmentToGraphFragment(data)
        homeAuthController?.navigate(direction)
    }


}