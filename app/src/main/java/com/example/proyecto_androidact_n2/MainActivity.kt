package com.example.proyecto_androidact_n2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {


    // El arreglo de la clase ItemsViewModel | ArrayList of class ItemsViewModel
    val data = ArrayList<ItemsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()

    }

    //Aqui puedes añadir datos(imagen, Titulo o nomre, Descripcion)
    fun llenarCardViewPC(){
        data.add(ItemsViewModel(R.drawable.haloinfinite_icon, "Halo: Infinite ", "Jefe Maestro"))
        data.add(ItemsViewModel(R.drawable.ageofempires_icon, "Age of Empire IV ", "Juego estrategia"))
        data.add(ItemsViewModel(R.drawable.citiesskyline_icon, "Cities Skyline", "Construye tu propia ciudad!"))
        data.add(ItemsViewModel(R.drawable.masseffect_icon, "Mass Effect: Andromeda", "Desubre seres alienigenas"))
        data.add(ItemsViewModel(R.drawable.starfield_icon, "Cities Skyline", "Descubre nuevos mundos y explora la galaxia"))
        data.add(ItemsViewModel(R.drawable.awayout_icon, "Cities Skyline", "Escapa del encierro con tu amigo"))
    }

    fun llenarCardViewNintendoSwitch(){
        data.add(ItemsViewModel(R.drawable.minecraft_icon, "Minecraft ", "Construye tu creatividad"))
        data.add(ItemsViewModel(R.drawable.marioodyssey_icon, "Mario Odyssey", "Gran juego de mario en nintendo switch"))
        data.add(ItemsViewModel(R.drawable.mariowonder_icon, "Super Mario Bros. Wonder", "Nuevo mario!"))
        data.add(ItemsViewModel(R.drawable.zelda_icon, "Zelda: Tears of the Kindgdom", "Continua la aventura de Breath of the Wild"))
        data.add(ItemsViewModel(R.drawable.seaofstar_icon, "Sea of Stars", "Disfruta de este juego de rol por turnos"))
        data.add(ItemsViewModel(R.drawable.luigismansion_icon, "Luigi´s Mansion 3", "Adentrate en la mansion con luigi en esta tercera entrega"))
    }

    fun llenarCardViewPlaystation5(){
        data.add(ItemsViewModel(R.drawable.spiderman_icon, "Marvel Spiderman 2", "Jefe Maestro"))
        data.add(ItemsViewModel(R.drawable.godofwar_icon, "God of War: Ragnarok", "Juego estrategia"))
        data.add(ItemsViewModel(R.drawable.crysis3_icon, "Crysis 3", "Construye tu propia ciudad!"))
        data.add(ItemsViewModel(R.drawable.granturismo_icon, "Gran Turismo 7", "Desubre seres alienigenas"))
        data.add(ItemsViewModel(R.drawable.returnal_icon, "Returnal", "Descubre nuevos mundos y explora la galaxia"))
        data.add(ItemsViewModel(R.drawable.horizon_icon, "Horizon: Forbidden West", "Escapa del encierro con tu amigo"))
    }

    fun llenarCardViewXbox(){
        data.add(ItemsViewModel(R.drawable.haloxbox_icon, "Halo: Infinite ", "Jefe Maestro"))
        data.add(ItemsViewModel(R.drawable.diablo4_icon, "Diablo IV ", "Juego estrategia"))
        data.add(ItemsViewModel(R.drawable.aplaguetale_icon, "A Plague Tale: Requiem", "Construye tu propia ciudad!"))
        data.add(ItemsViewModel(R.drawable.doometernal_icon, "Doom Eternal", "Desubre seres alienigenas"))
        data.add(ItemsViewModel(R.drawable.dragonageinquisiton_icon, "Dragong Age: Inquisition", "Descubre nuevos mundos y explora la galaxia"))
        data.add(ItemsViewModel(R.drawable.redfall_icon, "Redfall", "Escapa del encierro con tu amigo"))
    }


    fun initSpinner(){

        // accede a los items de la lista | Categoria es nombre de variable | el resorcesgetstrinarray() obtiene los items del archivo string.xml .
        val categoria = resources.getStringArray(R.array.Categorias)

        // accede al spinner ubicado en el layout, activity_main.xml .
        val spinner = findViewById<Spinner>(R.id.categorias)

        //Valida si no es nulo, hace el arrayadapter, toma el objeto seleccionado y lo almacena en la variable adapter.
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, categoria
            )
            spinner.adapter = adapter
            //selector de la categoria al darle clic o touch.
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val categoriaSeleccionada = spinner.selectedItem.toString()

                    when(categoriaSeleccionada){
                        "PC"-> {
                            Toast.makeText(this@MainActivity, "Ha seleccionado: PC", Toast.LENGTH_SHORT).show()
                            data.clear()
                            llenarCardViewPC()
                            initRecyclerView()

                        }
                        "Nintendo Switch"->{
                            Toast.makeText(this@MainActivity, "Ha seleccionado: Nintendo Switch", Toast.LENGTH_SHORT).show()
                            data.clear()
                            llenarCardViewNintendoSwitch()
                            initRecyclerView()
                        }
                        "PlayStation 5"-> {
                            Toast.makeText(this@MainActivity, "Ha seleccionado: PlayStation 5", Toast.LENGTH_SHORT).show()
                            data.clear()
                            llenarCardViewPlaystation5()
                            initRecyclerView()

                        }
                        "Xbox Series S|X"->{
                            Toast.makeText(this@MainActivity, "Ha seleccionado: Xbox Series S|X", Toast.LENGTH_SHORT).show()
                            data.clear()
                            llenarCardViewXbox()
                            initRecyclerView()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun initRecyclerView(){
        // busca el id del recyclerView y lo guarda en la variable
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        // Crea un layout de forma vertical | this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        //Esto pasara el array a nuestro adaptador |This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Configuración del adaptador con el recyclerview | Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

}