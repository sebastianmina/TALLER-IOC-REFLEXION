//Descripción de algunos agentes de Valorant
var initialPoks = [
    {
        clickCount: 0,
        name1: 'Astra',
        desc1: 'Astra, la agente ghanesa, controla las energías del cosmos para dar forma al campo de batalla a su antojo. Con pleno dominio de su forma astral y un gran talento para la anticipación estratégica, siempre va eones por delante de los movimientos de sus enemigos.',
    },
    {
        clickCount: 0,
        name1: 'Killjoy',
        desc1: 'Killjoy es una brillante agente alemana que se encarga de tomar el campo de batalla con un amplio arsenal de inventos. Si el daño de sus invenciones no detiene a los enemigos, la debilitación de sus robots los convertirá en presas fáciles.',
    },
    {
        clickCount: 0,
        name1: 'Skye',
        desc1: 'Skye y su manada de bestias se abren paso desde Australia y a través de territorio hostil. Sus creaciones obstaculizan los avances enemigos y su capacidad para curar a los demás se ocupa de que, a su lado, su equipo esté a salvo.',

    },
]

//Funcion que asigna las caracteristicas de cada pokemon
var Pok = function(data){
    this.clickCount = ko.observable(data.clickCount);
    this.region = ko.observable(data.region);
    this.gen = ko.observable(data.gen);

    //tipo de pokemon
    this.tipo = ko.computed(function(){
        var title;
        var clicks = this.clickCount();
        if(clicks<1){
            title = data.tipo1;
        }
        else if(clicks<2){
            title = data.tipo2;
        }
        else{
            title = data.tipo3;
        }
        return title;
    }, this);

    //nombre del pokemon
    this.name = ko.computed(function(){
        var title;
        var clicks = this.clickCount();
        if(clicks<1){
            title = data.name1;
        }
        else if(clicks<2){
            title = data.name2;
        }
        else{
            title = data.name3;
        }
        return title;
    }, this);

    //imagen del pokemon
    this.imgSrc = ko.computed(function(){
        var title;
        var clicks = this.clickCount();
        if(clicks<1){
            title = data.evo1;
        }
        else if(clicks<2){
            title = data.evo2;
        }
        else{
            title = data.evo3;
        }
        return title;
    }, this);

    //url de la imagen
    this.url = ko.computed(function(){
        var title;
        var clicks = this.clickCount();
        if(clicks<1){
            title = data.url1;
        }
        else if(clicks<2){
            title = data.url2;
        }
        else{
            title = data.url3;
        }
        return title;
    }, this);

    //descripcion del pokemon
    this.desc = ko.computed(function(){
        var title;
        var clicks = this.clickCount();
        if(clicks<1){
            title = data.desc1;
        }
        else if(clicks<2){
            title = data.desc2;
        }
        else{
            title = data.desc3;
        }
        return title;
    }, this);
}

var ViewModel = function(){
    var self = this;

    this.pokList = ko.observableArray([]);

    initialPoks.forEach(function(pokItem){
        self.pokList.push(new Pok(pokItem));
    });

    this.currentPok = ko.observable(this.pokList()[0]);

    this.incrementCounter = function(){
        if(self.currentPok().clickCount()<2){
            self.currentPok().clickCount(self.currentPok().clickCount() + 1);
            var count = 0;
            count++;
        }
    };

    this.decrementCounter = function(){
        if(self.currentPok().clickCount()>0){
            self.currentPok().clickCount(self.currentPok().clickCount() - 1);
            var count = 0;
            count--;
        }
    };

    this.setPok = function(clickedPok){
        self.currentPok(clickedPok);
    };

}

ko.applyBindings(new ViewModel());