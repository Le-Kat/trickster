package dev.enjarai.trickster.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class DestroyKnotCriterion extends AbstractCriterion<DestroyKnotCriterion.Conditions> {
    @Override
    public Codec<DestroyKnotCriterion.Conditions> getConditionsCodec() {
        return DestroyKnotCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player) {
        super.trigger(player, conditions -> true);
    }

    public record Conditions(Optional<LootContextPredicate> player) implements AbstractCriterion.Conditions {
        public static final Codec<DestroyKnotCriterion.Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(DestroyKnotCriterion.Conditions::player)
        ).apply(instance, DestroyKnotCriterion.Conditions::new)
        );

        @Override
        public void validate(LootContextPredicateValidator validator) {
            AbstractCriterion.Conditions.super.validate(validator);
        }
    }
}
