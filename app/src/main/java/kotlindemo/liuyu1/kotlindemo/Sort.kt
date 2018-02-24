package kotlindemo.liuyu1.kotlindemo

import java.util.*

/**
 * Created by liuyu1 on 2018/2/24.
 */
class Sort{
    fun  main(args : Array<String>){
        var array = intArrayOf(10,20,11,45,24,6,7,43,2)
        bubbleSort(array)

        println(Arrays.toString(array))
    }


    fun bubbleSort(array :IntArray){
        var i = 0;

        while (i < array .size){
            var j = i + 1;
            while (j < array.size){
                if(array[i] > array[j]){
                    var temp = array[i]
                    array[i] = array[j]
                    array[j] = temp
                }
                j ++
            }

            i ++;
        }
    }

    fun shellSort(arry: IntArray){

        var gap = arry.size/2
        while(gap > 0){
            println(gap)

            for (j in gap..arry.size - 1 step 1){

                var k = j - gap
                while (k >= 0 && arry[k] > arry[k + gap]){

                    var temp = arry[k]
                    arry[k] = arry[k + gap]
                    arry[k + gap] = temp
                    k  -= gap
                }
            }
            gap = gap /2 ;
        }

    }
}