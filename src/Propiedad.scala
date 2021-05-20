


class Propiedades(private var PropertyNo:String, var street:String, private var city:String, private var postcode: String, 
    private var types :String, private var rooms:Byte, private var rent: Integer, private var propietario:String, 
    private var personal:String, private var sucursal:String){
  
  def getPropertyNo() = PropertyNo
  def getstreet() = street
  def getcity() = city
  def getpostcode() = postcode
  def gettypes() = types
  def getrooms() = rooms
  def getrent() = rent
  def getpropietario() = propietario
  def getpersonal() = personal
  def getsucuarsal() = sucursal
  
  def setPropertyNo(propertyno:String){ PropertyNo = propertyno }
  def setstreet(street1:String){ street = street1 }
  def setcity(city1:String){ city = city1 }
  def setpostcode(postcode1:String){ postcode = postcode1 }
  def settypes(types1:String){ types = types1 }
  def setrooms(rooms1:Byte){ rooms = rooms1 }
  def setrent(rent1:Integer){ rent = rent1 }
  def setpropietario(propietario1:String){ propietario = propietario1 }
  def setpersonal(personal1:String){ personal = personal1 }
  def setsucursal(sucursal1:String){ sucursal = sucursal1 }
  
  
}

class PropiedadesExtensas(private var PropertyNo:String, var street:String, private var city:String, private var postcode: String, 
    private var types :String, private var rooms:Byte, private var rent: Integer, private var propietario:String, 
    private var personal:String, private var sucursal:String, private var cliente:String, private var fechavisita:String /*, private var comentario:String*/){
   
  def getPropertyNo() = PropertyNo
  def getstreet() = street
  def getcity() = city
  def getpostcode() = postcode
  def gettypes() = types
  def getrooms() = rooms 
  def getrent() = rent
  def getpropietario() = propietario 
  def getpersonal() = personal
  def getsucuarsal() = sucursal 
  def getcliente() = cliente
  def getfechavisita() = fechavisita
   
  def setPropertyNo(propertyno:String){ PropertyNo = propertyno }
  def setstreet(street1:String){ street = street1 }
  def setcity(city1:String){ city = city1 }
  def setpostcode(postcode1:String){ postcode = postcode1 }
  def settypes(types1:String){ types = types1 }
  def setrooms(rooms1:Byte){ rooms = rooms1 }
  def setrent(rent1:Integer){ rent = rent1 }
  def setpropietario(propietario1:String){ propietario = propietario1 }
  def setpersonal(personal1:String){ personal = personal1 }
  def setsucursal(sucursal1:String){ sucursal = sucursal1 }
  def setcliente(cliente1:String){cliente = cliente1}
  def setfechavisita(fechavisita1:String){fechavisita = fechavisita1}
  
}

