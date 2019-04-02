package game.phase;

public enum PhaseEnum {
    ARGUMENTPHASE {
        @Override
        public Phase getPhase() {
            return ArgumentPhase.getInstance();
        }
    },

    EXECUTEVOTEPHASE {
        @Override
        public Phase getPhase() {
            return ExecuteVotePhase.getInstance();
        }
    },

    MAFIAVOTEPHASE {
        @Override
        public Phase getPhase() {
            return MafiaVotePhase.getInstance();
        }
    },

    MORNINGPHASE {
        @Override
        public Phase getPhase() {
            return MorningPhase.getInstance();
        }
    },

    NIGHTPHASE {
        @Override
        public Phase getPhase() {
            return NightPhase.getInstance();
        }
    };

    public abstract Phase getPhase();
}
