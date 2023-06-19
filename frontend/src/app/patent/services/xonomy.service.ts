import { Injectable } from '@angular/core';
declare const Xonomy: any

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public fizickoLice = {
    elements: {
      podnosilac: {
        expanded: true,
        collapsed: true,
        actionParameter: '<podnosilac xsi:type="ks:TFizicko_Lice"></podnosilac>',
        menu:[
          {
            caption: 'Dodaj <adresa>',
            action: Xonomy.newElementChild,
            actionParameter: '<adresa></adresa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("adresa")
            }
          },
          {
            caption: 'Dodaj <broj_mobilnog_telefona>',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_mobilnog_telefona></broj_mobilnog_telefona>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj_mobilnog_telefona")
            }
          },
          {
            caption: 'Dodaj <broj_faksa>',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_faksa></broj_faksa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj_faksa")
            }
          },
          {
            caption: 'Dodaj <email>',
            action: Xonomy.newElementChild,
            actionParameter: '<email></email>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("email")
            }
          },
          {
            caption: 'Dodaj <puno_ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<puno_ime></puno_ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("puno_ime")
            }
          },
          {
            caption: 'Dodaj <drzavljanstvo>',
            action: Xonomy.newElementChild,
            actionParameter: '<drzavljanstvo></drzavljanstvo>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("drzavljanstvo")
            }
          },
        ],
      },
      puno_ime: {
        menu: [
          {
            caption: 'Dodaj ime',
            action: Xonomy.newElementChild,
              actionParameter: '<ime></ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("ime");
            }
          },
          {
            caption: 'Dodaj prezime',
            action: Xonomy.newElementChild,
            actionParameter: '<prezime></prezime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("prezime");
            }
          },
        ]
      },
      ime:{
        mustBeBefore:[],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ime");
        }
      },
      prezime:{
        mustBeBefore:[],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("prezime");
        }
      },
      adresa:{
        mustBeBefore:["broj_faksa","puno_ime", "email","drzavljanstvo"],
        menu:[
          {
            caption: 'Dodaj mesto',
            action: Xonomy.newElementChild,
            actionParameter: '<mesto></mesto>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("mesto");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<ulica></ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<broj></broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<postanski_broj></postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<drzava></drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("drzava");
            }
          },
        ]
      },
      broj_mobilnog_telefona:{
        mustBeBefore:["puno_ime", "drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj_mobilnog_telefona");
        }
      },
      broj_faksa:{
        mustBeBefore:["puno_ime","drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj_faksa");
        }
      },
      email:{
        mustBeBefore:["drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("email");
        }
      },
      drzavljanstvo:{
        menu:[
          {
            caption: 'Tip drzavljanstva',
            action: Xonomy.newElementChild,
            actionParameter: '<tip_drzavljanstva></tip_drzavljanstva>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("tip_drzavljanstva");
            }
          },
          {
            caption: 'Dodaj jmbg',
            action: Xonomy.newElementChild,
            actionParameter: '<jmbg></jmbg>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("jmbg");
            }
          },
          {
            caption: 'Dodaj broj pasosa',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_pasosa></broj_pasosa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj_pasosa");
            }
          },
        ]
      },
      tip_drzavljanstva:{
        mustBeBefore:["jmbg", "broj_pasosa"],
        oneliner: true,
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ['domace','strano'],
      },
      jmbg:{
        mustBeBefore:["broj_pasosa"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("jmbg");
        }
      },
      broj_pasosa:{
        mustBeBefore:[],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj_pasosa");
        }
      },
      mesto:{
        mustBeBefore:["ulica","broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("mesto");
        }
      },
      ulica:{
        mustBeBefore:["broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },
      broj:{
        mustBeBefore:["postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj");
        }
      },
      postanski_broj:{
        mustBeBefore:["drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("postanski_broj");
        }
      },
      drzava:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("drzava");
        }
      },
    }
  }

  public pravnoLice = {
    elements: {
      podnosilac: {
        expanded: true,
        collapsed: true,
        actionParameter: '<podnosilac xsi:type="ks:TFizicko_Lice"></podnosilac>',
        menu:[
          {
            caption: 'Dodaj <adresa>',
            action: Xonomy.newElementChild,
            actionParameter: '<adresa></adresa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("adresa")
            }
          },
          {
            caption: 'Dodaj <broj_mobilnog_telefona>',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_mobilnog_telefona></broj_mobilnog_telefona>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj_mobilnog_telefona")
            }
          },
          {
            caption: 'Dodaj <broj_faksa>',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_faksa></broj_faksa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj_faksa")
            }
          },
          {
            caption: 'Dodaj <email>',
            action: Xonomy.newElementChild,
            actionParameter: '<email></email>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("email")
            }
          },
          {
            caption: 'Dodaj <poslovno_ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<poslovno_ime></poslovno_ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("poslovno_ime")
            }
          },
        ],
      },
      poslovno_ime:{
        mustBeBefore:[],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("poslovno_ime");
        }
      },
      adresa:{
        mustBeBefore:["broj_faksa","puno_ime", "email","drzavljanstvo"],
        menu:[
          {
            caption: 'Dodaj mesto',
            action: Xonomy.newElementChild,
            actionParameter: '<mesto></mesto>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("mesto");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<ulica></ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<broj></broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<postanski_broj></postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<drzava></drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("drzava");
            }
          },
        ]
      },
      broj_mobilnog_telefona:{
        mustBeBefore:["puno_ime", "drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj_mobilnog_telefona");
        }
      },
      broj_faksa:{
        mustBeBefore:["puno_ime","drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj_faksa");
        }
      },
      email:{
        mustBeBefore:["drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("email");
        }
      },
      mesto:{
        mustBeBefore:["ulica","broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("mesto");
        }
      },
      ulica:{
        mustBeBefore:["broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },
      broj:{
        mustBeBefore:["postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj");
        }
      },
      postanski_broj:{
        mustBeBefore:["drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("postanski_broj");
        }
      },
      drzava:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("drzava");
        }
      },
    }
  }

}
