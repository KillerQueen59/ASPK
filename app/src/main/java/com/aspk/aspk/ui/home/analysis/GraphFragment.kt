package com.aspk.aspk.ui.home.analysis

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aspk.aspk.databinding.FragmentGraphBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class GraphFragment: Fragment() {
    private val args: GraphFragmentArgs by navArgs()
    lateinit var binding: FragmentGraphBinding
    var entriesGroup1: ArrayList<BarEntry> = ArrayList()
    var entriesGroup2: ArrayList<BarEntry> = ArrayList()
    var entriesGroup3: ArrayList<BarEntry> = ArrayList()
    var entriesGroup4: ArrayList<BarEntry> = ArrayList()
    var entriesGroup5: ArrayList<BarEntry> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            var iterator = 0f

            entriesGroup1.add(BarEntry(iterator++, args.data.data!![0][0].quantity.toFloat()))
            entriesGroup1.add(BarEntry(iterator++, args.data.data!![1][0].quantity.toFloat()))
            entriesGroup1.add(BarEntry(iterator++, args.data.data!![2][0].quantity.toFloat()))


            entriesGroup2.add(BarEntry(iterator++, args.data.data!![0][1].quantity.toFloat()))
            entriesGroup2.add(BarEntry(iterator++, args.data.data!![1][1].quantity.toFloat()))
            entriesGroup2.add(BarEntry(iterator++, args.data.data!![2][1].quantity.toFloat()))

            entriesGroup3.add(BarEntry(iterator++, args.data.data!![0][2].quantity.toFloat()))
            entriesGroup3.add(BarEntry(iterator++, args.data.data!![1][2].quantity.toFloat()))
            entriesGroup3.add(BarEntry(iterator++, args.data.data!![2][2].quantity.toFloat()))

            entriesGroup4.add(BarEntry(iterator++, args.data.data!![0][3].quantity.toFloat()))
            entriesGroup4.add(BarEntry(iterator++, args.data.data!![1][3].quantity.toFloat()))
            entriesGroup4.add(BarEntry(iterator++, args.data.data!![2][3].quantity.toFloat()))

            entriesGroup5.add(BarEntry(iterator++, args.data.data!![0][4].quantity.toFloat()))
            entriesGroup5.add(BarEntry(iterator++, args.data.data!![1][4].quantity.toFloat()))
            entriesGroup5.add(BarEntry(iterator++, args.data.data!![2][4].quantity.toFloat()))





            val barDataSet = BarDataSet(entriesGroup1, "Item 1")
            barDataSet.color = Color.parseColor("#F44336")
            val barDataSet1 = BarDataSet(entriesGroup2, "Item 2")
            barDataSet1.setColors(Color.parseColor("#9C27B0"))
            val barDataSet2 = BarDataSet(entriesGroup3, "Item 3")
            barDataSet2.setColors(Color.parseColor("#e241f4"))
            val barDataSet3 = BarDataSet(entriesGroup4, "Item 4")
            barDataSet3.setColors(Color.parseColor("#65847E"))
            val barDataSet4 = BarDataSet(entriesGroup5, "Item 5")
            barDataSet4.setColors(Color.parseColor("#009C86"))
            val months = arrayOf("Januari", "Februari", "Maret")
            val data = BarData(barDataSet, barDataSet1, barDataSet2,barDataSet3,barDataSet4)
            barChart.data = data

            val xAxis = barChart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(months)
            barChart.axisLeft.axisMinimum = 0f
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.setCenterAxisLabels(true)
            xAxis.isGranularityEnabled = true

            val barSpace = 0.02f
            val groupSpace = 0.3f
            val groupCount = 3

            //IMPORTANT *****

            //IMPORTANT *****
            data.barWidth = 0.15f
            barChart.xAxis.axisMinimum = 0f
            barChart.xAxis.axisMaximum =
                0 + barChart.barData.getGroupWidth(groupSpace, barSpace) * groupCount
            barChart.groupBars(0f, groupSpace, barSpace)
        }
    }


}