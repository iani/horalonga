
HoraLonga {
	var <window, <pitchClasses;
	
	*initClass {
		StartUp add: {
			Server.default.boot;
			this.new;
		}
	}

	*new {
		^super.new.init;
	}

	init {
		pitchClasses = Event make: {
			~do0_MIDI = 60 - 12;
			~do_MIDI = (0..5) * 12 + ~do0_MIDI;
			~re_MIDI = ~do_MIDI + 2;
			~fa_MIDI = ~do_MIDI + 5;
			~do = ~do_MIDI.midicps;
			~re = ~re_MIDI.midicps;
			~fa = ~fa_MIDI.midicps;
			~la5 = ~fa * 5 / 4;
			~si11 = ~fa * 11 / 8;
			~mib7 = ~fa * 7 / 8;
			~mi5 = ~do * 5 / 4;
			~fs5 = ~re * 5 / 4;
			~fs11 = ~do * 11 / 8;
			~sib7 = ~do * 7 / 4;
		};
		window = Window("Hora longa", Rect(10, 10, 800, 400)).front;
		window.view.layout = VLayout(
			*[\la5, \si11, \mib7, \mi5, \fs5, \fs11, \sib7].collect({ | pclass |
				HLayout(
					*pitchClasses[pclass].collect({ | p, i |
						Button()
						.states_([[format("% %", pclass, i)]])
						.action_({
							{ SinOsc.ar(p, 0.1) * EnvGen.kr(Env.perc, doneAction: 2) }.play;
						})
					})
				)
			})
		)
	}
}
