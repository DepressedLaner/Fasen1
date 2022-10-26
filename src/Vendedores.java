
import java.util.Hashtable;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;







public class Vendedores {


    public static void main(String[] args) throws Exception {

        BufferedReader Buffer = new BufferedReader (new InputStreamReader(System.in));
         //Variables
        String nombreVen;
        int nVendedores;
        String nombreCliente;
        String OpC;
        int Op;
        int CLiente;      
        int i;
        Enumeration Vendedor;
       String Ya;
   
        boolean Exit = false;

        //Creacion de listas
        LinkedList<String> VendedorOc=new LinkedList<>();
        LinkedList<String> VendedoresDisp=new LinkedList<>();

        LinkedList<String> ClientesCV=new LinkedList<>();
        LinkedList<String> ListClientes=new LinkedList<>();
        Hashtable<String, String> Vendedor_Cliente =new Hashtable<String, String>();



    System.out.println("Bienvenido a Papeleria pech\n"
                        + "Asignacion de personal\n");
        System.out.println("¿Cuantos vendedores estan disponibles?");
        nVendedores=Integer.parseInt(Buffer.readLine()); 
        for(i=1; i <= nVendedores; i++){
            System.out.println("Ingrese el Nombre del Vendedor " + i + " : ");
            nombreVen=Buffer.readLine();
            VendedoresDisp.add(nombreVen);
        }
        
        do{
            System.out.println("Bienvenido a Automotriz Pech\n");
            System.out.println("Escriba el numero de la opcion que necesite:\n"
                    + " 1) Registrar Cliente\n"
                    + " 2) Asignarle Vendedor al Cliente\n"
                    + " 3) Registrar Fin de visita del Cliente\n"
                    + " 4) Finalizar ");
                    
                    Op = Integer.parseInt(Buffer.readLine()); 

            switch(Op){
                //Registro del cliente
                case 1: System.out.println("Escribe el nombre de la persona que quieres registrar: ");
                nombreCliente = Buffer.readLine();
                ListClientes.add(nombreCliente);

                        //Pide Lo que hara el cliente Muestra de Producto o Compra de Producto y dependiendo se le asigna algo de cajon o no.
                        System.out.println("¿Quiere registrar a " + nombreCliente + " a una Muestra de Producto o a una compra de producto?\n" 
                                            + "Escriba MV si desea la Muestra de Producto\n"
                                            + "Escriba CV si desea la Compra del Producto\n");
                        OpC=Buffer.readLine();
                        if(OpC.equals("CV") || OpC.equals("Cv")){
                            Vendedor_Cliente.put(VendedoresDisp.get(0),nombreCliente);
                            VendedorOc.add(VendedoresDisp.get(0));
                            System.out.println("Ya que quiere una compra le pondremos a nuestro mejor vendedor: " + VendedoresDisp.get(0));
                            VendedoresDisp.remove(VendedoresDisp.get(0));
                            ClientesCV.add(ListClientes.get(0));
                            ListClientes.remove(ListClientes.getLast());

                        }else if(OpC.equals("MV") || OpC.equals("Mv")){
                            System.out.println("Se ha registrado como una muestra de Producto");
                        } else{
                            System.out.println("Fallo, ingrese una opcion valida");
                        }
                        break;
                        
                //Asignacion de Vendedor a cliente para muestreo de Producto
                case 2: if(VendedoresDisp.isEmpty() != true && ListClientes.isEmpty() != true){
                            Iterator<String> iterator=ListClientes.iterator();
                            i = 0;
                            System.out.println("");
                            while(iterator.hasNext()){
                                System.out.println(i + ": " + iterator.next());
                                i++;
                            }
                            System.out.println("¿A cual Cliente se le asignara Vendedor?");
                            CLiente=Integer.parseInt(Buffer.readLine());
                            Vendedor = Vendedor_Cliente.keys(); 
                            while(Vendedor.hasMoreElements()) {
                                Ya = (String) Vendedor.nextElement();
                                int x = 0;
                                if(x == 0){
                                    VendedoresDisp.addLast(Ya);
                                    VendedorOc.add(Ya);
                                    x = 1;
                                }
                            }
                            Vendedor_Cliente.put(VendedoresDisp.get(0),ListClientes.get(CLiente));
                            System.out.println("Se ha asignado al Vendedor" + VendedoresDisp.get(0));
                            VendedoresDisp.remove(VendedoresDisp.get(0));
                            ClientesCV.add(ListClientes.get(0));
                            ListClientes.remove(ListClientes.get(CLiente));
                                    
                        }else{
                            System.out.println("No tenemos Vendedores disponibles por el momento. Intentar mas tarde");
                        }
                        break;
                        
                //Registro de finalizacion para el Cliente
                case 3: if(ClientesCV.isEmpty() != true){
                    Vendedor = Vendedor_Cliente.keys(); 
                            while(Vendedor.hasMoreElements()) {
                                Ya = (String) Vendedor.nextElement();
                                int x = 0;
                                if(x == 0){
                                    VendedoresDisp.add(Ya);
                                    VendedorOc.remove(Ya);
                                    Vendedor_Cliente.remove(Ya);
                                    x = 1;
                                }  
                            }
                            System.out.println("El cliente " + ClientesCV.get(0) + "  ha finalizado su visita exitosamente");
                            ClientesCV.remove(0);
                        }else{
                            System.out.println("No hay clientes por el momento");
                        }
                        break;
                        
                //Salida del programa
                case 4: System.out.println("Agradecemos su preferencia vuelva pronto.");
                Exit = true;
                        break;
                default: System.out.println("Numero erroneo, intente de nuevo");
                        break;
            }
            System.out.println("");
            
        } while(Exit == false);    
    }
}
