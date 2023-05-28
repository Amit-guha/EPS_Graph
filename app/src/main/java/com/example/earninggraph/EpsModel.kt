package com.example.earninggraph

class EpsModel(
    val QuarterItem: String = "",
    val Year: String = "",
    var previousEps: Float = 0f,
    var latestEps: Float = 0f
){
    fun getListOfEpsGraphItem() : ArrayList<EpsModel>{
        val epsGraphListItem = ArrayList<EpsModel>()

       /* epsGraphListItem.add(EpsModel("Q3","FY21",100f,79f))
        epsGraphListItem.add(EpsModel("Q4","FY21",75f,170f))
        epsGraphListItem.add(EpsModel("Q1","FY22",48f,51f))
        epsGraphListItem.add(EpsModel("Q2","FY22",30f,33f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0f,60f))*/

        /*epsGraphListItem.add(EpsModel("Q3","FY21",500f,1500f))
        epsGraphListItem.add(EpsModel("Q4","FY21",1100f,700f))
        epsGraphListItem.add(EpsModel("Q1","FY22",1500f,981f))
        epsGraphListItem.add(EpsModel("Q2","FY22",300f,320f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0f,1455f))*/

        /*epsGraphListItem.add(EpsModel("Q3","FY21",2.21f,2.10f))
        epsGraphListItem.add(EpsModel("Q4","FY21",1.76f,1.79f))
        epsGraphListItem.add(EpsModel("Q1","FY22",1.45f,1.60f))
        epsGraphListItem.add(EpsModel("Q2","FY22",1.33f,2.39f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0.89f,0.95f))
*/
        epsGraphListItem.add(EpsModel("Q3","FY21",40.60f,75.10f))
        epsGraphListItem.add(EpsModel("Q4","FY21",8.76f,5.79f))
        epsGraphListItem.add(EpsModel("Q1","FY22",7.45f,45.60f))
        epsGraphListItem.add(EpsModel("Q2","FY22",60.33f,50.39f))
        epsGraphListItem.add(EpsModel("Q3","FY22",2.89f,0f))

        /*epsGraphListItem.add(EpsModel("Q3","FY21",100f,155f))
        epsGraphListItem.add(EpsModel("Q4","FY21",10f,26f))
        epsGraphListItem.add(EpsModel("Q1","FY22",35f,9f))
        epsGraphListItem.add(EpsModel("Q2","FY22",25f,10f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0f,49f))*/

        /*epsGraphListItem.add(EpsModel("Q3","FY21",10f,3.5f))
        epsGraphListItem.add(EpsModel("Q4","FY21",5f,2.9f))
        epsGraphListItem.add(EpsModel("Q1","FY22",4f,30.5f))
        epsGraphListItem.add(EpsModel("Q2","FY22",20f,1.8f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0f,0f))*/

        /*epsGraphListItem.add(EpsModel("Q3","FY21",10f,3.5f))
        epsGraphListItem.add(EpsModel("Q4","FY21",5f,2.9f))
        epsGraphListItem.add(EpsModel("Q1","FY22",-4f,-3.5f))
        epsGraphListItem.add(EpsModel("Q2","FY22",10f,1.8f))
        epsGraphListItem.add(EpsModel("Q3","FY22",0f,0f))*/

        return  epsGraphListItem
    }
}

