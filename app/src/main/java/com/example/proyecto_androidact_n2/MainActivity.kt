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
    fun llenarCardView(){
        data.add(ItemsViewModel(R.drawable.img_med_quim_caj_inventario, "Medicamentos ", "Te curo las heridas"))
        data.add(ItemsViewModel(R.drawable.vaultboy1, "Vault Boy serio ", "Soy el iconito joven vault"))
        data.add(ItemsViewModel(R.drawable.vaultboyrisa, "El Vault boy Riendo ", "En esta rio de felicidad!"))
    }

    fun llenarCardViewComida(){
        data.add(ItemsViewModel(R.drawable.papafritaicono, "Papitas Fritas ", "Bien sabrosas"))
    }

    fun llenarCardViewRopa(){
        data.add(ItemsViewModel(R.drawable.poleraicono, "Polera ", "10lukas"))
    }

    fun llenarCardViewViaje(){
        data.add(ItemsViewModel(R.drawable.viajesicono, "Viaje Premium", "A marte vamos!"))
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
                        "Comida"-> {
                            data.clear()
                            llenarCardView()
                            initRecyclerView()

                        }
                        "Deportes"->{
                            data.clear()
                            llenarCardViewComida()
                            initRecyclerView()
                        }
                        "Ropa"-> {
                            data.clear()
                            llenarCardViewRopa()
                            initRecyclerView()

                        }
                        "Viajes"->{
                            data.clear()
                            llenarCardViewViaje()
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